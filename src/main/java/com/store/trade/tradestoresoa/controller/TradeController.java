package com.store.trade.tradestoresoa.controller;

import com.store.trade.tradestoresoa.entity.Trade;
import com.store.trade.tradestoresoa.exception.serviceException.InvalidMaturityDateException;
import com.store.trade.tradestoresoa.exception.serviceException.LowerVersionTradeException;
import com.store.trade.tradestoresoa.service.TradeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trade")
public class TradeController {

    private final TradeService tradeService;

    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @PostMapping
    public ResponseEntity<Trade> addNewTrade(@RequestBody Trade trade)
            throws LowerVersionTradeException, InvalidMaturityDateException {
        tradeService.addNewTrade(trade);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
