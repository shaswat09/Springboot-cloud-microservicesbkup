package com.example.demo.domain;

import java.io.Serializable;

public class Organization implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String address;
	private String numberofemployees;
	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNumberofemployees() {
		return numberofemployees;
	}
	public void setNumberofemployees(String numberofemployees) {
		this.numberofemployees = numberofemployees;
	}

	
	
}
