package com.db.tradestorage.controller;

import com.db.tradestorage.exception.InvalidTradeException;
import com.db.tradestorage.model.Trade;
import com.db.tradestorage.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class TradeController {
    @Autowired
    TradeService tradeService;

    @PostMapping("/trade")
    public ResponseEntity<String> tradeValidateStore(@RequestBody Trade trade){

       if(tradeService.isExisting(trade)) {
           tradeService.persist(trade);
       }else{
           throw new InvalidTradeException(trade.getTradeId()+"  Trade Id is not found");
       }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/trade")
    public ResponseEntity<List<Trade>> findAllTrades(){
        return  ResponseEntity.ok(tradeService.findAll());
    }
}
