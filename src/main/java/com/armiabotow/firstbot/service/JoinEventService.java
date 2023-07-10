package com.armiabotow.firstbot.service;

import discord4j.core.object.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Locale;

@Service
@RequiredArgsConstructor
@Slf4j
public class JoinEventService {

    // lepiej do template name uzyc stringa a nie enuma
    private final ResourceLoader resourceLoader;

    // todo niech template zawiera link https://www.youtube.com/watch?v=RaY-4JgOy0k
    @SneakyThrows
    public Mono<Void> sendPrivateMessage(User user) {
        // todo niech advice kontroller zajmie sie tym Excreption a nie trycatch/sneky throws

        return user.getPrivateChannel()
                .block()// wyjscie z event loopa i zwrocenie private channel
                .createMessage(this.buildPrivateMessage(user.getUsername(), "PRIVATE_INVITE_MESSAGE")) // create messahe spowrotem wraca do event loopa
                // w sumie nie powino byc block tylko np map/flatmap z wywolana metoda event message
                .then();
    }






    public void takeFromResources() throws URISyntaxException, IOException {
        final URL url = getClass().getClassLoader().getResource("db/migration/V2__insert_private_invite_message.sql");
        final Path path = Paths.get(url.toURI());
        final String s = Files.readString(path);
        // s == zawartosc teg pliku w stringu
        //final Resource resource = resourceLoader.getResource("classpath: db/migration/V2__insert_private_invite_message.sql");

    }

    private String buildPrivateMessage(String username, String templateName) {
        // string jest obrze sjkonstruowany ale nie wyciagam  bazy body
        // final String body = "Cześć [(${name})] :D Witaj na serwerze! Wypełnij ankietę!: https://forms.gle/TG2ULJjX8kz5sbL4A";
        final String body = templateService.getByName(templateName).getBody();
        final HashMap<String, Object> variables = new HashMap<>();
        variables.put("name", username);
        final Context context = new Context(Locale.getDefault(), variables);
        // context to rzeczy do zmiany w templatcie a body to template
        return iTemplateEngine.process(body, context);
    }



    private final TemplateService templateService;
    private final ITemplateEngine iTemplateEngine;
    private String useOfTemplateEngine(String username) {
        final String body = templateService.getByName("PRIVATE_INVITE_MESSAGE").getBody(); // Dostajemy taki template: Cześć [(${name})] :D Witaj na serwerze!
        final HashMap<String, Object> variables = new HashMap<>(); //
        variables.put("name", username);
        final Context context = new Context(Locale.getDefault(), variables);  // context to rzeczy do zmiany w templatcie
        return iTemplateEngine.process(body, context);
    }

}
