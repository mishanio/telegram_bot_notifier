package com.michael.telegram_bot_notifier.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.michael.telegram_bot_notifier.client.CbrClient;
import com.michael.telegram_bot_notifier.service.ExchangeService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class ExchangeServiceImpl implements ExchangeService {

    private final CbrClient client;

    public ExchangeServiceImpl(CbrClient client) {
        this.client = client;
    }


    @Override
    public String getExchangeRate(String currency) {

        return client.getCurrencyRates()
            .flatMap(xml -> extractCurrencyFromXml(xml, currency))
            .orElse("");
    }

    private Optional<String> extractCurrencyFromXml(String xml, String code) {
        XmlMapper xmlMapper = new XmlMapper();
        try {
            JsonNode node = xmlMapper.readTree(xml.getBytes());
            JsonNode valutes = node.get("Valute");
            return StreamSupport.stream(valutes.spliterator(), false)
                .filter(valute -> valute.get("CharCode").textValue().equals(code.toUpperCase()))
                .findFirst()
                .map(valute -> valute.get("Value").textValue());
        } catch (IOException e) {
//            throw new RuntimeException(e);
            return Optional.empty();
        }

    }

}
