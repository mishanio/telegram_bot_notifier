package com.michael.telegram_bot_notifier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TelegramBotNotifierApplication {

	public static void main(String[] args) {
		SpringApplication.run(TelegramBotNotifierApplication.class, args);
	}

}
