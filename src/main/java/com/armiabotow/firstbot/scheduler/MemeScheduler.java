package com.armiabotow.firstbot.scheduler;

import com.armiabotow.firstbot.config.DiscordPropertiesConfig;
import com.armiabotow.firstbot.service.MeetingService;
import com.armiabotow.firstbot.service.TemplateService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.discordjson.json.MessageCreateRequest;
import discord4j.rest.util.MultipartRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reactor.util.function.Tuples;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MemeScheduler {

    private final DiscordPropertiesConfig discordPropertiesConfig;
    private final GatewayDiscordClient gatewayDiscordClient;
    private final TemplateService templateService;
    private final MeetingService meetingService;

    // cron = "0 0 9 * * SUN"
    @Scheduled(fixedRate = 1000)
    public void sendReminders() throws DocumentException {
        // todo wysylaj pdfa
        String msg = templateService.getByName("SCHEDULED_REMINDER").getBody();

        LocalDateTime now = LocalDateTime.now();

        msg = msg + meetingService.getAllMeetingsNextWeek(now, now.with(TemporalAdjusters.next(DayOfWeek.SUNDAY))).toString();

        final Document document = new Document();

        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();
        document.add(new Paragraph(msg));
        document.close();

        final byte[] bytes = byteArrayOutputStream.toByteArray();

        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

        final MultipartRequest<MessageCreateRequest> pdf = MultipartRequest.ofRequestAndFiles(
                MessageCreateRequest.builder().content("komentarz").build(),
                List.of(Tuples.of("mojawlasnanazwapliku.pdf", byteArrayInputStream)));



        gatewayDiscordClient.rest()//  daje mi dostep do serwera
                .getChannelById(Snowflake.of(discordPropertiesConfig.getChannelId())) // wybiera kanał tekstowy na który ma zostać wyslana wiadomosc
                .createMessage(pdf)
                .subscribe(); // Metoda subscribe() rejestruje subscriber w celu otrzymywania powiadomień o zmianach
                // lub zdarzeniach związanych z wiadomością, na przykład o pomyślnym wysłaniu wiadomości lub wystąpieniu błędu podczas jej wysyłania.




        // todo jednak stworz modal dla nowych uzytkownikow. musisz w koncu skads pobrac maila
        // https://github.com/Discord4J/Discord4J/blob/master/core/src/test/java/discord4j/core/ExampleModal.java
    }

    // todo jak pobieram plik s3 to zapisac go i sprawdzic czy dziala
}