package com.example.sqlitetestone.entity;

public class userlogin {

	private int _id;
	private String username;
	private String password;
	private int loginNUm;
	private String loginDatetime;
	
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getLoginNUm() {
		return loginNUm;
	}
	public void setLoginNUm(int loginNUm) {
		this.loginNUm = loginNUm;
	}
	public String getLoginDatetime() {
		return loginDatetime;
	}
	public void setLoginDatetime(String loginDatetime) {
		this.loginDatetime = loginDatetime;
	}
	public userlogin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public userlogin(String username, String password, int loginNUm,
			String loginDatetime) {
		super();
		this.username = username;
		this.password = password;
		this.loginNUm = loginNUm;
		this.loginDatetime = loginDatetime;
	}	
}
