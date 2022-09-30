package com.playdata.petCommunity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PetCommunityApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetCommunityApplication.class, args);
	}

}
