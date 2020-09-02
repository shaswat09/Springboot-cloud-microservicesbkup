package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.web.client.RestTemplate;

import com.example.demo.rest.service.OrganizationChangeModel;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableOAuth2Sso
@EnableCircuitBreaker
public class SpringCloudDiscoveryEurekaClientApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudDiscoveryEurekaClientApplication.class, args);
	}
	
	@LoadBalanced
	@Bean    // The @LoadBalanced annotation tells Spring Cloud to create a Ribbon backed RestTemplate class.
	public RestTemplate getRestTemplate(){
	return new RestTemplate();
	}
	
	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
	JedisConnectionFactory jedisConnFactory = new
	JedisConnectionFactory();
	jedisConnFactory.setHostName("localhost");
	jedisConnFactory.setPort(6379);
	jedisConnFactory.setUsePool(true);

	return jedisConnFactory;
	}
	
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
	RedisTemplate<String, Object> template = new RedisTemplate<String,
	Object>();
	template.setConnectionFactory(jedisConnectionFactory());
	template.setEnableTransactionSupport(true);

	return template;
	}
	
	/*@LoadBalanced
	@Bean
	RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
	return restTemplateBuilder
	.setConnectTimeout(1000)
	.setReadTimeout(1000)
	.build();
	}*/
	
/*	@LoadBalanced
	@Bean
	@Autowired    //Authorization” HTTP header is injected into the application call out to the Organization service.
	public OAuth2RestTemplate loadBalancedOauth2RestTemplate(OAuth2ClientContext oauth2ClientContext, OAuth2ProtectedResourceDetails details){
			return new OAuth2RestTemplate(details, oauth2ClientContext);  
//The OAuth2RestTemplate is a drop-in replacement for the standard RestTemplate and	handles the propagation of the OAuth2 access token.
	}*/
}