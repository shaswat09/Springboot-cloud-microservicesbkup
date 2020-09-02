package com.example.demo.repo;

import com.example.demo.domain.Organization;

public interface OrganizationRedisRepository {

	void saveOrganization(Organization org);
	void updateOrganization(Organization org);
	void deleteOrganization(Integer organizationId);
	Organization findOrganization(Integer organizationId);
}
