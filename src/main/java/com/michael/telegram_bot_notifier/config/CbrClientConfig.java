package com.michael.telegram_bot_notifier.config;

import com.michael.telegram_bot_notifier.client.CbrClient;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CbrClientConfig {

    @Bean
    OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }

    @Bean
    CbrClient cbrClient(@Value("${cbr.currency.rates.xml.url}") String url, OkHttpClient okHttpClient) {
        return new CbrClient(url, okHttpClient);
    }
}
