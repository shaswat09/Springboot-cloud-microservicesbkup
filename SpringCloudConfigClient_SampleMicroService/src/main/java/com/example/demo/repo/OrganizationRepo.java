package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.pojo.Organization;

@Repository
public interface OrganizationRepo extends JpaRepository<Organization, Integer> {

}
