package com.store.trade.tradestoresoa.job;

import com.store.trade.tradestoresoa.repository.TradeRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@Configuration
@EnableScheduling
public class TradeUpdater {

    final TradeRepository tradeRepository;

    public TradeUpdater(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void perform() {
        tradeRepository.findAll().iterator().forEachRemaining(trade -> {
            if (trade.getMaturityDate().compareTo(new Date()) < 0) {
                trade.setExpired(true);
                tradeRepository.save(trade);
            }
        });
    }
}
