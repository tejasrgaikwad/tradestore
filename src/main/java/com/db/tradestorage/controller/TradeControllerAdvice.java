package com.db.tradestorage.controller;

import com.db.tradestorage.exception.ErrorResponse;
import com.db.tradestorage.exception.InvalidTradeException;
import org.springframework.hateoas.mediatype.vnderrors.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Optional;

@ControllerAdvice
@RequestMapping(produces = "application/vnd.error+json")
public class TradeControllerAdvice extends ResponseEntityExceptionHandler{
    @ExceptionHandler(InvalidTradeException.class)
    public ResponseEntity<ErrorResponse> notFoundException(final InvalidTradeException e) {
        ErrorResponse response = new ErrorResponse(LocalDateTime.now(), e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }



    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity assertionException(final IllegalArgumentException e) {
        return ResponseEntity.unprocessableEntity().body(e.getLocalizedMessage());
    }



}
