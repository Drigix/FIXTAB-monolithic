package com.fixtab.app;

import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

@SpringBootApplication
@EnableJpaRepositories("com.fixtab.app.*")
@ComponentScan(basePackages = {"com.fixtab.app.*"})
@EntityScan("com.fixtab.app*")
public class AppApplication {
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
//
//		JSONObject sectrectsJson = new JSONObject(secret);
//		String username = sectrectsJson.getString("username");
//			String password = sectrectsJson.getString("password");
//		System.setProperty("spring.datasource.username", username);
//		System.setProperty("spring.datasource.password", password);
	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

}
