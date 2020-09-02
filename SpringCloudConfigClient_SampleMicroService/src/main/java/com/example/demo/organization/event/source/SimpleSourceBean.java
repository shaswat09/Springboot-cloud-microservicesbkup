package com.example.demo.organization.event.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.example.demo.pojo.OrganizationChangeModel;

@Component
public class SimpleSourceBean {

	private Source source;
	
	@Autowired
	private CustomSourceChannels custom;
	
	@Autowired
	public SimpleSourceBean(Source source){
	this.source = source;
	}
	
	public void publishOrgChange(String action, Integer orgId){
		OrganizationChangeModel organizationChangeModel=new OrganizationChangeModel(OrganizationChangeModel.class.getTypeName(), action, orgId);
	//	custom.outboundOrg().send(MessageBuilder.withPayload(organizationChangeModel).build());
		source.output().send(MessageBuilder.withPayload(organizationChangeModel).build());
	}
}
