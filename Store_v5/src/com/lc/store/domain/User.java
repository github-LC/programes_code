package com.lc.store.domain;

import java.util.Date;

/**
 * 创建用户对象存储和传输用户信息
 * @author user LC
 *
 */
public class User {
	
	private String username;
	private String password;
	private String name;//用户姓名
	private String email;
	private String telephone;
	private Date birthday;
	private String sex;
	private String uid;
	private String code;
	private int state;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	public User() {
		
	}
	
	public User(String username, String password, String name, String email, String telephone, Date birthday,
			String sex, String uid, String code, int state) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.telephone = telephone;
		this.birthday = birthday;
		this.sex = sex;
		this.uid = uid;
		this.code = code;
		this.state = state;
	}
	
	
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", name=" + name + ", email=" + email
				+ ", telephone=" + telephone + ", birthday=" + birthday + ", sex=" + sex + ", uid=" + uid + ", code="
				+ code + ", state=" + state + "]";
	}
	
	
	
}
