package com.michael.telegram_bot_notifier.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Optional;

@Component
public class ExchangeRatesBot extends TelegramLongPollingBot {

    private final static Logger log = LoggerFactory.getLogger(ExchangeRatesBot.class);

    public ExchangeRatesBot(@Value("${bot.token}") String token) {
        super(token);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) {
            return;
        }
        String userCommand = update.getMessage().getText();
        Optional<BotCommands> maybeCommand = BotCommands.parse(userCommand);
        if (maybeCommand.isEmpty()) {
            Long chatId = update.getMessage().getChatId();
            log.info("unknown user command! chatId:{}, command: {}", chatId, userCommand);
            return;
        }
        BotCommands botCommands = maybeCommand.get();
        switch (botCommands) {
            case START -> startCommand(update.getMessage());
            case EUR -> {
            }
            case USD -> {
            }
            case HELP -> {
            }
        }
    }


    @Override
    public String getBotUsername() {
        return "telegram_bot_notifier";
    }

    private void sendMessage(Long chatId, String text) {

        SendMessage sendMessage = new SendMessage(String.valueOf(chatId), text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Error sending message! chatId: {}, text: {}", chatId, text, e);
        }
    }

    private void startCommand(Message message) {
        Long chatId = message.getChatId();
        String userName = message.getChat().getUserName();
        String text = """
            Привет %s
            Команды:
            /eur
            /usd
            """.formatted(userName);

        sendMessage(chatId, text);
    }
}
