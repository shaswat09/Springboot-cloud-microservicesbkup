package com.example.demo.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.handler.annotation.SendTo;

import com.example.demo.repo.OrganizationRedisRepository;

//@EnableBinding(Processor.class)    // Single input and output
@EnableBinding(Sink.class)  // Single input
public class OrganizationChangeHandler {

	@Autowired
	OrganizationRedisRepository organizationRedisRepo;
	// If we want to implement logic to receive events and send response back
	//@StreamListener(Processor.INPUT)
//	@SendTo(Processor.OUTPUT)
	// OR
	//@Transformer(inputChannel=Processor.INPUT, outputChannel=Processor.OUTPUT)
	@StreamListener(Sink.INPUT)
	public void loggerSink(OrganizationChangeModel changeModel){
		System.out.println("Received an event for Organization db changes. for orgid "+ changeModel.getOrganizationId());
	if(changeModel.getAction().equalsIgnoreCase("delete")){
		organizationRedisRepo.deleteOrganization(changeModel.getOrganizationId());
	}
if(changeModel.getAction().equalsIgnoreCase("update")){
	organizationRedisRepo.deleteOrganization(changeModel.getOrganizationId());
	}
	}
	
	/*Consuming messages conditionally
Assuming we would like to treat messages incoming to the same message channel differently, we may use conditional dispatching.
	@StreamListener(target = Processor.INPUT, condition = "headers['processor']=='account'")
public void receiveOrder(Order order) throws JsonProcessingException {
LOGGER.info("Order received from account: {}", mapper.writeValueAsString(order));
// ...
}
@StreamListener(target = Processor.INPUT, condition = "headers['processor']=='product'")
public void receiveOrder(Order order) throws JsonProcessingException {
LOGGER.info("Order received from product: {}", mapper.writeValueAsString(order));
// ...
}
	 */ 	
}
