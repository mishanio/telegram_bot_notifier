package com.michael.telegram_bot_notifier;

import com.michael.telegram_bot_notifier.client.CbrClient;
import com.michael.telegram_bot_notifier.config.CbrClientConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CbrClientManualTest.TestConfig.class, CbrClientConfig.class})
public class CbrClientManualTest {

  @Configuration
  static class TestConfig {
      @Bean
      public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
          PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
          propertySourcesPlaceholderConfigurer.setPropertySources(new  );
          return propertySourcesPlaceholderConfigurer;
      }
  }

    @Autowired
    public CbrClient client;


    @Test
    public void testGetInfo() {
        Optional<String> currencyRates = client.getCurrencyRates();
        Assertions.assertTrue(currencyRates.isPresent());
        System.out.println(currencyRates.get());
    }

}
