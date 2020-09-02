package com.example.demo.microservice;

import java.net.URI;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.organization.event.source.SimpleSourceBean;

import com.example.demo.pojo.Organization;
import com.example.demo.repo.OrganizationRepo;

import brave.ScopedSpan;
import brave.Span;
import brave.Tracer;

@RestController
public class ConfigClient {

	@Value("${user.role}")
	private String role;
	
	@Autowired
	private SimpleSourceBean simpleSourceBean;
	
	private static final Logger logger = LoggerFactory.getLogger(ConfigClient.class);
	
	@Autowired
	OrganizationRepo orgRepo;
	
	@Autowired
	Tracer tracer;
	
	@RequestMapping(
		      value = "/whoami/{username}", 
		      method = RequestMethod.GET)
		    public String whoami(@PathVariable("username") String username) {
		logger.info("Shaswat------------------"); // This will print app name, trace id, Span id, boolean which says whether this info is sent to zipkin
		        return String.format("Hello!You're %s and you'll become a(n) %s...\n", username, role);
		    }
	
	@ResponseStatus(org.springframework.http.HttpStatus.CREATED)
	@PostMapping("/organization")
	public ResponseEntity<Void> addOrg(@RequestBody Organization organization){
		Integer i=organization.getId();
		Organization o=orgRepo.save(organization);
if(i!=null){
	simpleSourceBean.publishOrgChange("update", i);
	}
	URI uri=ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(o.getId()).toUri();
return ResponseEntity.created(uri).build();	
	}
	
	@DeleteMapping(value = "/org/{id}")
    public ResponseEntity<Integer> deleteOrganization(@PathVariable Integer id) {
	//	orgRepo.deleteById(id);
		simpleSourceBean.publishOrgChange("delete", id);

        return new ResponseEntity<>(id, org.springframework.http.HttpStatus.OK);
    }
	
	@GetMapping(value = "/fetch/org/{id}")
    public Organization fetchOrganization(@PathVariable Integer id) {
//ScopedSpan span=	tracer.startScopedSpan("getOrgDbCall");
Span newSpan=tracer.nextSpan().name("getOrgDbCall");
logger.info("In Organization service getOrg call");		
try{
Organization organization=	orgRepo.findById(id).get();
return organization;
}finally{
	newSpan.tag("peer.service", "My SQL");
	newSpan.annotate("Fetched Organization complete---------");
	newSpan.finish();	
}
//return organization ;
	
    }
}
