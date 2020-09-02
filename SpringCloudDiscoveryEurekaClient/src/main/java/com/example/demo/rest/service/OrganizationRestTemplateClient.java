package com.example.demo.rest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.domain.Organization;
import com.example.demo.repo.OrganizationRedisRepository;

@Service
public class OrganizationRestTemplateClient {

	@Autowired
 OrganizationRedisRepository organizationRedisRepository;
	
	@Autowired
	RestTemplate restTemplate;

	private static final Logger logger =
			LoggerFactory.getLogger(OrganizationRestTemplateClient.class);
	
	public Organization checkRedisCache(Integer orgId){
	try{
		return	organizationRedisRepository.findOrganization(orgId);
	} catch(Exception ex){
		logger.error("Error encountered while trying to retrieve organization {} check Redis Cache.Exception {}", orgId, ex);
		return null;
	}
	}
	
	public Organization getOrganization(Integer orgId){
Organization organization= checkRedisCache(orgId);
if(organization!=null){
	return organization;
}
logger.debug("Unable to locate organization from the redis cache: {}.", orgId);
ResponseEntity<Organization> restExchange =restTemplate.exchange("http://config-client/fetch/org/{id}",HttpMethod.GET,null, Organization.class, orgId);
Organization latestOrg=restExchange.getBody();

if (latestOrg!=null) {
cacheOrganizationObject(latestOrg);
}

return latestOrg;
	}
	
	private void cacheOrganizationObject(Organization org) {
		try {
			organizationRedisRepository.saveOrganization(org);
		}catch (Exception ex){
		logger.error("Unable to cache organization {} in Redis. Exception {}", org.getId(), ex);
		}
		}
}
