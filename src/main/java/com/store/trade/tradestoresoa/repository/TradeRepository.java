package com.store.trade.tradestoresoa.repository;

import com.store.trade.tradestoresoa.entity.Trade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeRepository extends CrudRepository<Trade, Long> {

}
