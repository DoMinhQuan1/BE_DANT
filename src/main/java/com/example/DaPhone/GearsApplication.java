package com.example.DaPhone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = "com.example.DaPhone")
@EntityScan("com.example.DaPhone.Entity")
@EnableJpaRepositories("com.example.DaPhone.Repository")
public class GearsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GearsApplication.class, args);
	}

}
 