package com.armiabotow.firstbot.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommandUtils {

    public static boolean containsWord(String sentence, String search) {
        return sentence.toLowerCase().indexOf(search.toLowerCase()) != -1;
    }

    public static LocalDateTime parseDate(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public static String getPart(String content, int first, int last) {
        return content.substring(first, last + 1);
    }
}
