package com.armiabotow.firstbot.listener.impl;

import com.armiabotow.firstbot.command.CommandRuleFactory;
import com.armiabotow.firstbot.listener.EventListener;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MessageCreateEventListenerImpl implements EventListener<MessageCreateEvent> {
    private final CommandRuleFactory commandRuleFactory;

    // pakiety zawsze pojedynczymi slowami
    // 2 wzorce projektowe ktore moglyby sie nadawac: ruleEnginePattern i command
    // uzyjemy ruleEnginePattern (jakby warjacja strategy ktora zamiast getytype ma matches) + factory
    @Override
    public Class<MessageCreateEvent> getEventType() {
        return MessageCreateEvent.class;
    }

    // !addMeeting name yyyy-MM-dd HH:mm description
    // !addMeeting spot 2024-12-12 11:00 description
    // !deleteMeeting name
    @Override
    public Mono<Void> execute(MessageCreateEvent event) {
        return Mono.just(event.getMessage())
                .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
                .flatMap(Message::getChannel)
                .flatMap(channel -> channel.createMessage(commandRuleFactory.getCommandRule(event.getMessage()).apply(event.getMessage())))
                .then();
               // .flatMap(message -> commandRuleFactory.getCommandRule(message).apply(message));
    }
}