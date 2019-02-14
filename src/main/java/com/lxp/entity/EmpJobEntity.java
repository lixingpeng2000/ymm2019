package com.lxp.entity;

public class EmpJobEntity {
	private String id;
	private String ename;
	private String tel;
	private String jname;
	private String jcity;
	private String jclass;
	private String nature;
	private String duty;
	private String req;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getJname() {
		return jname;
	}
	public void setJname(String jname) {
		this.jname = jname;
	}
	public String getJcity() {
		return jcity;
	}
	public void setJcity(String jcity) {
		this.jcity = jcity;
	}
	public String getJclass() {
		return jclass;
	}
	public void setJclass(String jclass) {
		this.jclass = jclass;
	}
	public String getNature() {
		return nature;
	}
	public void setNature(String nature) {
		this.nature = nature;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getReq() {
		return req;
	}
	public void setReq(String req) {
		this.req = req;
	}
	public EmpJobEntity(String id, String ename, String tel, String jname, String jcity, String jclass, String nature,
			String duty, String req) {
		super();
		this.id = id;
		this.ename = ename;
		this.tel = tel;
		this.jname = jname;
		this.jcity = jcity;
		this.jclass = jclass;
		this.nature = nature;
		this.duty = duty;
		this.req = req;
	}
	

}
