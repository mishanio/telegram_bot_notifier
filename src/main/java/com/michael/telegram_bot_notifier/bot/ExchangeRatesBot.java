package com.michael.telegram_bot_notifier.bot;

import com.michael.telegram_bot_notifier.service.ExchangeService;
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

import static com.michael.telegram_bot_notifier.bot.BotCommands.EUR;
import static com.michael.telegram_bot_notifier.bot.BotCommands.USD;

@Component
public class ExchangeRatesBot extends TelegramLongPollingBot {

    private final ExchangeService exchangeService;

    private final static Logger log = LoggerFactory.getLogger(ExchangeRatesBot.class);

    public ExchangeRatesBot(@Value("${bot.token}") String token, ExchangeService exchangeService) {
        super(token);
        this.exchangeService = exchangeService;
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
            case EUR -> getExchangeCommand(update.getMessage(), EUR.toString());
            case USD -> getExchangeCommand(update.getMessage(), USD.toString());
            case HELP -> {
            }
        }
    }

    private void getExchangeCommand(Message message, String currency) {
        Long chatId = message.getChatId();
        String exchangeRate = exchangeService.getExchangeRate(currency);
        sendMessage(chatId, exchangeRate);
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
