package com.lc.crm.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 创建实体类
 * @author user LC
 *
 */
public class Customer {
	/**
	 *`cust_id` BIGINT(32) NOT NULL AUTO_INCREMENT COMMENT '客户编号(主键)',
  	 *`cust_name` VARCHAR(32) NOT NULL COMMENT '客户名称(公司名称)',
  	 *`cust_source` VARCHAR(32) DEFAULT NULL COMMENT '客户信息来源',
  	 *`cust_industry` VARCHAR(32) DEFAULT NULL COMMENT '客户所属行业',
  	 *`cust_level` VARCHAR(32) DEFAULT NULL COMMENT '客户级别',
  	 *`cust_phone` VARCHAR(64) DEFAULT NULL COMMENT '固定电话',
  	 *`cust_mobile` VARCHAR(16) D
	 */
	private Long cust_id;
	private String cust_name;
	private BaseDict sourceBaseDict;
	private BaseDict industryBaseDict;
	private BaseDict levelBaseDict;
	private String cust_phone;
	private String cust_mobile;
	private Long cust_user_id;
	/* private String cust_linkman; */
	private Long cust_create_id;
	private String cust_filepath;
	private Set<LinkMan> linkMan = new HashSet<LinkMan>();
	
	public String getCust_filepath() {
		return cust_filepath;
	}
	public void setCust_filepath(String cust_filepath) {
		this.cust_filepath = cust_filepath;
	}
	public Long getCust_id() {
		return cust_id;
	}
	public void setCust_id(Long cust_id) {
		this.cust_id = cust_id;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public BaseDict getSourceBaseDict() {
		return sourceBaseDict;
	}
	public void setSourceBaseDict(BaseDict sourceBaseDict) {
		this.sourceBaseDict = sourceBaseDict;
	}
	public BaseDict getIndustryBaseDict() {
		return industryBaseDict;
	}
	public void setIndustryBaseDict(BaseDict industryBaseDict) {
		this.industryBaseDict = industryBaseDict;
	}
	public BaseDict getLevelBaseDict() {
		return levelBaseDict;
	}
	public void setLevelBaseDict(BaseDict levelBaseDict) {
		this.levelBaseDict = levelBaseDict;
	}
	public String getCust_phone() {
		return cust_phone;
	}
	public void setCust_phone(String cust_phone) {
		this.cust_phone = cust_phone;
	}
	public String getCust_mobile() {
		return cust_mobile;
	}
	public void setCust_mobile(String cust_mobile) {
		this.cust_mobile = cust_mobile;
	}
	public Long getCust_user_id() {
		return cust_user_id;
	}
	public void setCust_user_id(Long cust_user_id) {
		this.cust_user_id = cust_user_id;
	}

	public Set<LinkMan> getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(Set<LinkMan> linkMan) {
		this.linkMan = linkMan;
	}
	public Long getCust_create_id() {
		return cust_create_id;
	}
	public void setCust_create_id(Long cust_create_id) {
		this.cust_create_id = cust_create_id;
	}
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Customer(Long cust_id, String cust_name, BaseDict sourceBaseDict, BaseDict industryBaseDict,
			BaseDict levelBaseDict, String cust_phone, String cust_mobile, Long cust_user_id, Long cust_create_id,
			String cust_filepath, Set<LinkMan> linkMan) {
		super();
		this.cust_id = cust_id;
		this.cust_name = cust_name;
		this.sourceBaseDict = sourceBaseDict;
		this.industryBaseDict = industryBaseDict;
		this.levelBaseDict = levelBaseDict;
		this.cust_phone = cust_phone;
		this.cust_mobile = cust_mobile;
		this.cust_user_id = cust_user_id;
		this.cust_create_id = cust_create_id;
		this.cust_filepath = cust_filepath;
		this.linkMan = linkMan;
	}
	
}
