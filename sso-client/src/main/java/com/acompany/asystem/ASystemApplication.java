package com.acompany.asystem;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.acompany.asystem.domain.User;
import com.acompany.asystem.service.UserRepository;

@SpringBootApplication
public class ASystemApplication {
	//
	private static final Logger log = LoggerFactory.getLogger(ASystemApplication.class);

	@Value("${systemName}")
	private String systemName;
	
	@Value("${server.port}")
	private int serverPort;
	
	public static void main(String[] args) {
		SpringApplication.run(ASystemApplication.class);
	}
	
	@Bean
	public CommandLineRunner initUser(UserRepository repository) {
		//
		return (args) -> {
			//
			log.debug("\n## systemName : {}", systemName);
			log.debug("\n## systemPort : {}", serverPort);
			
			repository.save(createUser("tsong"));
			repository.save(createUser("jmpark"));
			repository.save(createUser("jkkang"));
			repository.save(createUser("test"));

			for (User user : repository.findAll()) {
				log.debug("\n## found user : {}", user);
			}
			User user = repository.findOne("jmpark");
			log.debug("\n## user : {}", user);
		};
	}
	
	private User createUser(String userName) {
		//
		return new User(userName, getRandomAge(), String.format("%s in %s", userName, systemName));
	}
	
	private int getRandomAge() {
		//
		return 20 + new Random().nextInt(30);
	}
}
