package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;

@SpringBootApplication
@EnableEurekaClient
@EnableBinding(Source.class)
@RefreshScope //access a refresh endpoint that will force the Spring Boot application to reread its application configuration.
public class SpringCloudConfigClientSampleMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudConfigClientSampleMicroServiceApplication.class, args);
	}
}
