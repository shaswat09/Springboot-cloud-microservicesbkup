package com.example.demo.repo;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Organization;

@Repository
public class OrganizationRedisRepositoryImpl implements OrganizationRedisRepository {

	private static final String HASH_NAME="organization";
	private RedisTemplate<String, Organization> redisTemplate;
	private HashOperations hashOperations;
	
	public OrganizationRedisRepositoryImpl(){
		super();
	}
	
	@Autowired
	private OrganizationRedisRepositoryImpl(RedisTemplate redisTemplate){
		this.redisTemplate=redisTemplate;
	}
	
	@PostConstruct
	public void init(){
		hashOperations=	redisTemplate.opsForHash();
	}
	
	@Override
	public void saveOrganization(Organization org) {
		hashOperations.put(HASH_NAME, org.getId(), org);
	}

	@Override
	public void updateOrganization(Organization org) {
		hashOperations.put(HASH_NAME, org.getId(), org);
	}

	@Override
	public void deleteOrganization(Integer organizationId) {
		hashOperations.delete(HASH_NAME, organizationId);
	}

	@Override
	public Organization findOrganization(Integer organizationId) {
	
		return (Organization) hashOperations.get(HASH_NAME, organizationId);
	}

}
