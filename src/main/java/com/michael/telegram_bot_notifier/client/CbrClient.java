package com.michael.telegram_bot_notifier.client;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.util.Optional;

public class CbrClient {

    private final String url;
    private final OkHttpClient client;

    public CbrClient(String url,  OkHttpClient client) {
        this.url = url;
        this.client = client;
    }

    public Optional<String> getCurrencyRates() {
        Request request = new Request.Builder().url(url).build();
        try (var response =client.newCall(request).execute()) {
            ResponseBody body = response.body();
            return body == null ? Optional.empty() : Optional.of(body.string());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
