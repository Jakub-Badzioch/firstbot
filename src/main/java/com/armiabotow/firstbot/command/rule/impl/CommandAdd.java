package com.armiabotow.firstbot.command.rule.impl;

import com.armiabotow.firstbot.command.rule.CommandRule;
import com.armiabotow.firstbot.model.impl.Meeting;
import com.armiabotow.firstbot.service.MeetingService;
import com.armiabotow.firstbot.service.TemplateService;
import discord4j.core.object.entity.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.thymeleaf.ITemplateEngine;

import static com.armiabotow.firstbot.utils.CommandUtils.containsWord;
import static com.armiabotow.firstbot.utils.CommandUtils.parseDate;

@Component
@RequiredArgsConstructor
public class CommandAdd implements CommandRule {

    private static final String ADD = "!addMeeting";
    private final MeetingService meetingService;

    @Override
    public boolean matches(Message message) {
        return containsWord(message.getContent(), ADD);
    }

    // !addMeeting name yyyy-MM-dd HH:mm description
    @Override
    public String apply(Message message) {
        final String[] strings = message.getContent().split(" ");
        strings[2].concat(" ").concat(strings[3]);
        final StringBuilder sb = new StringBuilder();
        for (int i = 4; i < strings.length; i++) {
            sb.append(" ");
            sb.append(strings[i]);
        }
        meetingService.add(
                Meeting.builder()
                        .name(strings[1]) // getPart(message.getContent(), 12, 15)
                        .dateTime(parseDate(strings[2].concat(" ").concat(strings[3])))// parseDate(getPart(message.getContent(), 17, 32))
                        .description(sb.toString())// getPart(message.getContent(), 33, message.getContent().length() - 1)
                        .build()
        );
        return "Spotkanie zostalo dodane";
        //  return message.getChannel().doOnNext(channel -> channel.createMessage("Spotkanie zostalo dodane")).then();
    }
}
