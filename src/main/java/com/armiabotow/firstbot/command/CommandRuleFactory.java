package com.armiabotow.firstbot.command;

import com.armiabotow.firstbot.command.rule.CommandRule;
import com.armiabotow.firstbot.exception.RuleNotSupportedException;
import discord4j.core.object.entity.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CommandRuleFactory {

    private final List<CommandRule> rules;

    public CommandRule getCommandRule(Message message) {
        return rules.stream()
                .filter(commandRule -> commandRule.matches(message))
                .findFirst()
                .orElseThrow(() -> new RuleNotSupportedException(message.getContent()));
    }
}
