package com.armiabotow.firstbot;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@RequiredArgsConstructor
public class FirstbotApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstbotApplication.class, args);
	}
}
