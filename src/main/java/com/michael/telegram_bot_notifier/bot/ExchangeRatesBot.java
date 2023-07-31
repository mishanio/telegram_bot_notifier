package com.michael.telegram_bot_notifier.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class ExchangeRatesBot extends TelegramLongPollingBot {

    public ExchangeRatesBot(String token) {
        super(token);
    }
    @Override
    public void onUpdateReceived(Update update) {

    }

    @Override
    public String getBotUsername() {
        return null;
    }
}
