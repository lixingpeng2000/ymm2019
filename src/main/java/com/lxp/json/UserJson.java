package com.lxp.json;

public class UserJson {
	private String username;
	private String pwd;
	private String token;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public UserJson(String username, String pwd, String token) {
		super();
		this.username = username;
		this.pwd = pwd;
		this.token = token;
	}
	
}
