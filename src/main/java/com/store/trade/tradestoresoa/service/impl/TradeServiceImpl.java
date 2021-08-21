package com.store.trade.tradestoresoa.service.impl;

import com.store.trade.tradestoresoa.entity.Trade;
import com.store.trade.tradestoresoa.exception.serviceException.InvalidMaturityDateException;
import com.store.trade.tradestoresoa.exception.serviceException.LowerVersionTradeException;
import com.store.trade.tradestoresoa.repository.TradeRepository;
import com.store.trade.tradestoresoa.service.TradeService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TradeServiceImpl implements TradeService {

    private final TradeRepository tradeRepository;

    public TradeServiceImpl(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    @Override
    public void addNewTrade(Trade trade) throws LowerVersionTradeException, InvalidMaturityDateException{
        validateMaturityDate(trade);
        if(isTradePresent(trade)) {
            validateTradeVersion(trade);
        }
        tradeRepository.save(trade);
    }

    public boolean isTradePresent(Trade trade) {
        return tradeRepository.findById(trade.getTradeId()).isPresent();
    }

    public void validateTradeVersion(Trade trade) throws LowerVersionTradeException {
        Trade existingTrade = tradeRepository.findById(trade.getTradeId()).get();
        if (existingTrade.getVersion() > trade.getVersion())
            throw new LowerVersionTradeException("Lower Version Trade is not Accepted.");
    }

    public void validateMaturityDate(Trade trade) throws InvalidMaturityDateException {
        Date currentDate = new Date();
        if (currentDate.compareTo(trade.getMaturityDate()) > 0)
            throw new InvalidMaturityDateException("Maturity date is before the current date.");
    }
}
