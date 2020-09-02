package com.example.demo.pojo;

public class OrganizationChangeModel {

	private String type;
    private String action;
    private Integer organizationId;
  
    
    
    
	public OrganizationChangeModel() {
		super();
	}
	public OrganizationChangeModel(String type, String action, Integer organizationId) {
		super();
		this.type = type;
		this.action = action;
		this.organizationId = organizationId;
		
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Integer getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	
}
