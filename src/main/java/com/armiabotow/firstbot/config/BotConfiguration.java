package com.armiabotow.firstbot.config;

import com.armiabotow.firstbot.listener.EventListener;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.Event;
import discord4j.gateway.intent.IntentSet;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.util.List;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class BotConfiguration {
    // nie latamy po calyn okodzie akby sie cos zmienilo tyljo jest 1 klasa do zmiany
    private final DiscordPropertiesConfig discordPropertiesConfig;

    @Bean
    @SneakyThrows
    // opakowuje wszystko co jest w metodzie w try catcha i exception ktory przechwyci opakowuje w runtime excp i go zwraca
    public <T extends Event> GatewayDiscordClient gatewayDiscordClient(List<EventListener<T>> eventListeners) {
        GatewayDiscordClient client = DiscordClientBuilder.create(discordPropertiesConfig.getToken())
                .build()
                .gateway() //
                .setEnabledIntents(IntentSet.all()) //
                .login()
                .block();
        for (EventListener<T> listener : eventListeners) {
            client.on(listener.getEventType())
                    .flatMap(listener::execute)
                    .onErrorResume(throwable -> { // lapie error ale wnawia proces dalej
                        log.error(throwable.getMessage(), throwable);
                        return Mono.empty();
                    })
                    .subscribe();
        }
        return client;
    }
}

