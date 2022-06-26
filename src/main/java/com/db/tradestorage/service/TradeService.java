package com.db.tradestorage.service;

import com.db.tradestorage.dao.TradeDao;
import com.db.tradestorage.dao.TradeRepository;
import com.db.tradestorage.exception.InvalidTradeException;
import com.db.tradestorage.model.Trade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TradeService {

    private static final Logger log = LoggerFactory.getLogger(TradeService.class);

    @Autowired
    TradeDao tradeDao;

    @Autowired
    TradeRepository tradeRepository;

    public boolean isExisting(Trade trade){
        if(validateMaturityDate(trade)) {
            // Trade existing Trade = tradeDao.findTrade(trade.getTradeId());
            Optional<Trade> existingTrade = tradeRepository.findById(trade.getTradeId());
             if (existingTrade.isPresent()) {
                 return validateVersion(trade, existingTrade.get());
             }else{
                 return true;
             }
         }
         return false;
    }

    private boolean validateVersion(Trade trade,Trade oldTrade) {
        //validation 1  During transmission if the
        // lower version is being received by the store it will reject the trade and throw an exception.
        if(trade.getVersion() >= oldTrade.getVersion()){
            return true;
        }
        throw new InvalidTradeException("New version ["+trade.getVersion()+"] cannot be lower than current version:["+oldTrade.getVersion()+"]");
    }

    //2.	Store should not allow the trade which has less maturity date then today date
    private boolean validateMaturityDate(Trade trade){
         if(trade.getMaturityDate().isBefore(LocalDate.now()))
             throw new InvalidTradeException("Maturity date cannot be less than current date.");
         return true;
    }



    public void  persist(Trade trade){
        trade.setCreatedDate(LocalDate.now());
        tradeRepository.save(trade);
    }

    public List<Trade> findAll(){
       return  tradeRepository.findAll();
    }

    public void updateExpiryFlagOfTrade(){
        tradeRepository.findAll().stream().forEach(t -> {
                if (!validateMaturityDate(t)) {
                    t.setExpiredFlag("Y");
                    log.info("Trade which needs to updated {}", t);
                    tradeRepository.save(t);
                    log.info("Trade updated {}", t);
                }
            });
        }


}
