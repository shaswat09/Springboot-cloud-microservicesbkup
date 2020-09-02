package com.example.demo.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Organization;
import com.example.demo.feigninterface.OrganizationFeignClient;
import com.example.demo.rest.service.FeignService;
import com.example.demo.rest.service.OrganizationRestTemplateClient;

@RestController
public class FeignController {

	@Autowired
	private FeignService feignService;
	
	@Autowired
	private OrganizationRestTemplateClient orgRestTemplateService;
	
	@RequestMapping(
		      value = "/retrive/{username}", 
		      method = RequestMethod.GET)
		    public String getUsername(@PathVariable("username") String username) {
		        return feignService.getUserName(username);
		    } 
	
	
	@GetMapping(value = "/org/{id}")
	public Organization getOrganization(@PathVariable("id") Integer id){
		return orgRestTemplateService.getOrganization(id);
	}
	
}
