package com.lc.crm.domain;
/**
 * CRM管理系统的用户实体
 * @author user LC
 *
 */
public class User {

	/*
	 * CREATE TABLE `sys_user` ( `user_id` bigint(32) NOT NULL AUTO_INCREMENT
	 * COMMENT '用户id', `user_code` varchar(32) NOT NULL COMMENT '用户账号', ``
	 * varchar(64) NOT NULL COMMENT '用户名称', `user_password` varchar(32) NOT NULL
	 * COMMENT '用户密码', `user_state` char(1) NOT NULL COMMENT '1:正常,0:暂停', PRIMARY
	 * KEY (`user_id`) ) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8
	 */
	
	private Long user_id;
	private String user_code;
	private String user_name;
	private String user_password;
	private String user_state;
	private String user_email;
	
	public Long getUser_id() {
		return user_id;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public String getUser_code() {
		return user_code;
	}
	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_state() {
		return user_state;
	}
	public void setUser_state(String user_state) {
		this.user_state = user_state;
	}
	
	
	public User(Long user_id, String user_code, String user_name, String user_password, String user_state,
			String user_email) {
		super();
		this.user_id = user_id;
		this.user_code = user_code;
		this.user_name = user_name;
		this.user_password = user_password;
		this.user_state = user_state;
		this.user_email = user_email;
	}
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_code=" + user_code + ", user_name=" + user_name + ", user_password="
				+ user_password + ", user_state=" + user_state + ", user_email=" + user_email + "]";
	}
	
	
}
