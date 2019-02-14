package com.lxp.entity;

public class UserRoleEntity {
	
	private String userName;
	private String roleName;
	private int weight;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	@Override
	public String toString() {
		return "UserRoleEntity [userName=" + userName + ", roleName=" + roleName + ", weight=" + weight + "]";
	}
	

}
