package com.michael.telegram_bot_notifier.bot;

import java.util.Arrays;
import java.util.Optional;

public enum BotCommands {
    START("/start"),
    EUR("/eur"),
    USD("/usd"),
    HELP("/help");

    private final String command;

    BotCommands(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static Optional<BotCommands> parse(String command) {
        return Arrays.stream(BotCommands.values()).filter(c -> c.getCommand().equals(command)).findFirst();
    }
}
