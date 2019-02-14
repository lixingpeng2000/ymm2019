package com.lxp.entity;

import java.io.Serializable;

public class EmployEntity  implements Serializable{
	private String empNo;
	private String ename;
	private String tel;
	private String mail;
	private String jname;
	private String jcity;
	private String cv;

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

	public EmployEntity(String ename, String tel, String mail) {
		super();
		this.ename = ename;
		this.tel = tel;
		this.mail = mail;
	}
	
	public EmployEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCv() {
		return cv;
	}

	public void setCv(String cv) {
		this.cv = cv;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
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
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getEmpNo() {
		return empNo;
	}

	@Override
	public String toString() {
		return "EmployEntity [empNo=" + empNo + ", ename=" + ename + ", tel=" + tel + ", mail=" + mail + ", jname="
				+ jname + ", jcity=" + jcity + ", cv=" + cv + "]";
	}
	

}
