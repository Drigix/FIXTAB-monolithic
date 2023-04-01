package com.fixtab.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.fixtab.app.*")
@ComponentScan(basePackages = {"com.fixtab.app.*"})
@EntityScan("com.fixtab.app*")
public class AppApplication {

	public static void main(String[] args) {
		String secretName = "ACCESS_KEY";
//		Region region = Region.of("eu-north-1");
//
//		// Create a Secrets Manager client
//		SecretsManagerClient client = SecretsManagerClient.builder()
//				.region(region)
//				.build();
//
//		GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
//				.secretId(secretName)
//				.build();
//
//		GetSecretValueResponse getSecretValueResponse;
//
//		try {
//			getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
//		} catch (Exception e) {
//			// For a list of exceptions thrown, see
//			// https://docs.aws.amazon.com/secretsmanager/latest/apireference/API_GetSecretValue.html
//			throw e;
//		}
//
//		String secret = getSecretValueResponse.secretString();

		SpringApplication.run(AppApplication.class, args);
	}

}
