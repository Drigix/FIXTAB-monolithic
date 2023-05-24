package com.fixtab.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
@EnableJpaRepositories("com.fixtab.app.*")
@ComponentScan(basePackages = {"com.fixtab.app.*"})
@EntityScan("com.fixtab.app*")
public class AppApplication {
	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		String username = dotenv.get("DB_USERNAME");
        String password = dotenv.get("DB_PASSWORD");
        System.setProperty("DB_USERNAME", username);
        System.setProperty("DB_PASSWORD", password);
		SpringApplication.run(AppApplication.class, args);
	}

}
