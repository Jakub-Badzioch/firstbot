package com.armiabotow.firstbot.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter // dla springa zeby mogl sam sobie wstawic te zmienne
@ConfigurationProperties(prefix = "discord")// prefix zmienna ktora propwadzi do innych w ymlu
public class DiscordPropertiesConfig {

    private String token;
    private String channelId;
    private String guildId;
}
