package com.armiabotow.firstbot.listener.impl;

import com.armiabotow.firstbot.listener.EventListener;
import com.armiabotow.firstbot.service.JoinEventService;
import discord4j.core.event.domain.guild.MemberJoinEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberJoinEventListenerImpl implements EventListener<MemberJoinEvent> {

    private final JoinEventService joinEventService;

    @Override
    public Class<MemberJoinEvent> getEventType() {
        return MemberJoinEvent.class;
    }

    @Override
    public Mono<Void> execute(MemberJoinEvent event) {
        return joinEventService.sendPrivateMessage(event.getMember());
    }
}

// sciagnij biblioteke itext pdf do depen