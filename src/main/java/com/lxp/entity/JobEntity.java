package com.lxp.entity;

public class JobEntity {
	private String id;
	private String jname;
	private String jcity;
	private String dept;
	private String jclass;
	private String duty;
	private String req;
	private String nature;
	private String createDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getJclass() {
		return jclass;
	}

	public void setJclass(String jclass) {
		this.jclass = jclass;
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

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public JobEntity(String jname, String jcity, String dept, String jclass, String duty, String req, String nature) {
		super();
		this.jname = jname;
		this.jcity = jcity;
		this.dept = dept;
		this.jclass = jclass;
		this.duty = duty;
		this.req = req;
		this.nature = nature;
	}

	public JobEntity() {
		super();
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "JobEntity [id=" + id + ", jname=" + jname + ", jcity=" + jcity + ", dept=" + dept + ", jclass=" + jclass
				+ ", duty=" + duty + ", req=" + req + ", nature=" + nature + ", createDate=" + createDate + "]";
	}
	

}
