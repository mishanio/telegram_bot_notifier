package com.michael.telegram_bot_notifier.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class ExchangeRatesBot extends TelegramLongPollingBot {

    public ExchangeRatesBot(@Value("${bot.token}") String token) {
        super(token);
    }
    @Override
    public void onUpdateReceived(Update update) {

    }

    @Override
    public String getBotUsername() {
        return "telegram_bot_notifier";
    }
}
