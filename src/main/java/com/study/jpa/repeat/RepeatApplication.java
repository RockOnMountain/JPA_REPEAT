package com.study.jpa.repeat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class RepeatApplication {
	public static void main(String[] args) {
		SpringApplication.run(RepeatApplication.class, args);
	}

}
