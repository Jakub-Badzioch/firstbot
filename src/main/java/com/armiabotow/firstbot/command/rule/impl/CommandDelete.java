package com.armiabotow.firstbot.command.rule.impl;

import com.armiabotow.firstbot.command.rule.CommandRule;
import com.armiabotow.firstbot.service.MeetingService;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static com.armiabotow.firstbot.utils.CommandUtils.containsWord;
import static com.armiabotow.firstbot.utils.CommandUtils.getPart;

@Component
@RequiredArgsConstructor
public class CommandDelete implements CommandRule {

    private static final String DELETE = "!deleteMeeting";
    private final MeetingService meetingService;

    @Override
    public boolean matches(Message message) {
        return containsWord(message.getContent(), DELETE);
    }

    @Override
    public String apply(Message message) {
        meetingService.deleteByName(getPart(message.getContent(), 15, message.getContent().length() - 1));
        return "Spotkanie zostalo usuniete";
        // todo homework w zakomentowany returnie zamoiast doonnext zrobic flatmape. i maybe po flatmapie wywolac thenreturn
        //return message.getChannel().doOnNext(channel -> channel.createMessage("Spotkanie zostalo usuniete")).then();
    }
}
