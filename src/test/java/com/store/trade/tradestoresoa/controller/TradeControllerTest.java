package com.store.trade.tradestoresoa.controller;

import com.store.trade.tradestoresoa.entity.Trade;
import com.store.trade.tradestoresoa.exception.serviceException.InvalidMaturityDateException;
import com.store.trade.tradestoresoa.exception.serviceException.LowerVersionTradeException;
import com.store.trade.tradestoresoa.service.TradeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Calendar;
import java.util.Date;

import static org.mockito.Mockito.times;

public class TradeControllerTest {
    @InjectMocks
    private TradeController tradeController;

    @Mock
    TradeService tradeService;

    @Mock
    Trade trade;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddNewTrade() throws LowerVersionTradeException, InvalidMaturityDateException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, Calendar.OCTOBER, 21);
        Date maturityDate = calendar.getTime();
        trade = new Trade(104L,3,"CP1","B2",new Date(),maturityDate,false);
        tradeController.addNewTrade(trade);
        Mockito.verify(tradeService, times(1)).addNewTrade(trade);
        // Mockito.verify(tradeService, times(1)).validateMaturityDate(trade);

    }

    @Test
    public void testUpdateExistingTrade() throws LowerVersionTradeException, InvalidMaturityDateException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, Calendar.OCTOBER, 20);
        Date maturityDate = calendar.getTime();
        trade = new Trade(101L,2,"CP1","B1", new Date(), maturityDate,false);
        tradeController.addNewTrade(trade);
        Mockito.verify(tradeService, times(1)).addNewTrade(trade);
    }

    @Test
    public void testLowerVersionTradeException() throws LowerVersionTradeException, InvalidMaturityDateException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, Calendar.OCTOBER, 21);
        Date maturityDate = calendar.getTime();
        trade = new Trade(102L,1,"CP1","B1", new Date(), maturityDate,false);
        tradeController.addNewTrade(trade);
        Mockito.verify(tradeService, times(1)).addNewTrade(trade);
    }

    @Test
    public void testInvalidMaturityDateException() throws LowerVersionTradeException, InvalidMaturityDateException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, Calendar.AUGUST, 12);
        Date maturityDate = calendar.getTime();
        trade = new Trade(105L,2,"CP2","B2", new Date(), maturityDate, false);
        tradeController.addNewTrade(trade);
        Mockito.verify(tradeService, times(1)).addNewTrade(trade);
    }

    @Test
    public void testGetTrade() {

    }
}
