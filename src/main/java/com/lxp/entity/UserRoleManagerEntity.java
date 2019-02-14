package com.lxp.entity;

import java.util.List;

public class UserRoleManagerEntity {
	private String userName;
	private List roles;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List getRoles() {
		return roles;
	}
	public void setRoles(List roles) {
		this.roles = roles;
	}
	public UserRoleManagerEntity(String userName, List roles) {
		super();
		this.userName = userName;
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "UserRoleManagerEntity [userName=" + userName + ", roles=" + roles + "]";
	}
	

}
