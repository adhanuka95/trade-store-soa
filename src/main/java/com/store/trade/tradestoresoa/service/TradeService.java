package com.store.trade.tradestoresoa.service;

import com.store.trade.tradestoresoa.entity.Trade;
import com.store.trade.tradestoresoa.exception.serviceException.InvalidMaturityDateException;
import com.store.trade.tradestoresoa.exception.serviceException.LowerVersionTradeException;

public interface TradeService {
    void addNewTrade(Trade trade) throws LowerVersionTradeException, InvalidMaturityDateException;
}
