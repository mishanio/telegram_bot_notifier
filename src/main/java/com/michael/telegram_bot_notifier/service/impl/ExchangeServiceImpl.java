package com.michael.telegram_bot_notifier.service.impl;

import com.michael.telegram_bot_notifier.client.CbrClient;
import com.michael.telegram_bot_notifier.service.ExchangeService;
import org.springframework.stereotype.Service;

@Service
public class ExchangeServiceImpl implements ExchangeService {

    private final CbrClient client;

    public ExchangeServiceImpl(CbrClient client) {
        this.client = client;
    }


    @Override
    public String getUsdExchangeRate() {
        return null;
    }

    @Override
    public String getEurExchangeRate() {
        return null;
    }

    private String exctractCurrrencyValueFromXml(String xml, String xpathExpression) {
        return null;
    }

}
