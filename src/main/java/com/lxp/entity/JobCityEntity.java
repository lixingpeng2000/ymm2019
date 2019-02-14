package com.lxp.entity;

public class JobCityEntity {
	private String jcity;
	private int jnum;
	public String getJcity() {
		return jcity;
	}
	public void setJcity(String jcity) {
		this.jcity = jcity;
	}
	public int getJnum() {
		return jnum;
	}
	public void setJnum(int jnum) {
		this.jnum = jnum;
	}
	public JobCityEntity(String jcity, int jnum) {
		super();
		this.jcity = jcity;
		this.jnum = jnum;
	}
	public JobCityEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
