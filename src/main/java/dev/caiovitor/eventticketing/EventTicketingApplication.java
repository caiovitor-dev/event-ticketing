package dev.caiovitor.eventticketing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EventTicketingApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventTicketingApplication.class, args);
	}

}
