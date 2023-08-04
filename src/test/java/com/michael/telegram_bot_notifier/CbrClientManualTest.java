package com.michael.telegram_bot_notifier;

import com.michael.telegram_bot_notifier.client.CbrClient;
import com.michael.telegram_bot_notifier.config.CbrClientConfig;
import com.michael.telegram_bot_notifier.service.ExchangeService;
import com.michael.telegram_bot_notifier.service.impl.ExchangeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CbrClientConfig.class, ExchangeServiceImpl.class})
@TestPropertySource("/application.properties")
public class CbrClientManualTest {

    @Autowired
    public CbrClient client;

    @Autowired
    ExchangeService exchangeService;


    @Test
    public void testGetInfo() {
        Optional<String> currencyRates = client.getCurrencyRates();
        Assertions.assertTrue(currencyRates.isPresent());
        System.out.println(currencyRates.get());
    }

    @Test
    public void testGetUsdInfo() {
        String euroRate = exchangeService.getEurExchangeRate();
        System.out.println(euroRate);
    }

}
