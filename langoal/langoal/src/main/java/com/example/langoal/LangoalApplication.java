package com.example.langoal;

import com.example.langoal.repository.TutorRepository;
import com.example.langoal.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import com.example.langoal.entities.User;
import com.example.langoal.entities.Tutor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LangoalApplication {

	public static void main(String[] args) {

		SpringApplication.run(LangoalApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(UserRepository userRepository) {
		return args -> {
		};
	}

}
