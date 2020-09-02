package com.example.demo.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.feigninterface.OrganizationFeignClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class FeignService {

	@Autowired
	private OrganizationFeignClient organizationFeignClient;
	
	@HystrixCommand(
			threadPoolKey = "licenseByOrgThreadPool",
			commandProperties=
			{@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="12000"),
			@HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="10"),
			@HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="75"),
			@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="7000")
			},
			fallbackMethod = "buildFallbackLicenseList", ignoreExceptions=IllegalAccessException.class,
			threadPoolProperties =
			{@HystrixProperty(name = "coreSize",value="30"),
			@HystrixProperty(name="maxQueueSize", value="10")}
			)
	public String getUserName(String userName){
		 return organizationFeignClient.whoami(userName);
	}
	
	public  String buildFallbackLicenseList(String username){
		return "defaultUsername"; // This method is invoked if call to remote microservice will failed with the help of circuit breaker 
	}
}
