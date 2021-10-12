package br.com.anderson.southsystem.desafiobackvotos;

import java.util.Date;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableKafka
@SpringBootApplication
public class DesafioBackVotosApplication {
	
	private static final Logger log = LoggerFactory.getLogger(DesafioBackVotosApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DesafioBackVotosApplication.class, args);
	}
	
	@PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
        log.info("TimeZone: " + new Date());
    }

}
