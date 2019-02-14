package com.lxp.json;

import java.util.List;

import com.lxp.entity.UserRoleManagerEntity;

public class UserRoleManagerJson {
	private String code;
	private UserRoleManagerEntity[] dataList;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public UserRoleManagerEntity[] getDataList() {
		return dataList;
	}
	public void setDataList(UserRoleManagerEntity[] dataList) {
		this.dataList = dataList;
	}
	public UserRoleManagerJson(String code, UserRoleManagerEntity[] allUserList) {
		super();
		this.code = code;
		this.dataList = allUserList;
	}
	
	

}
