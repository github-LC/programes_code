package com.lc.crm.domain;

import java.util.Date;

/**
 * 客户满意度实体
 * @author user LC
 *
 */
public class CustomerSatisfaction {

	/*
	 
Create Table

CREATE TABLE `cst_customer_satisfaction` (
  `satisfaction_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `price_satisfaction` varchar(10) DEFAULT NULL COMMENT '性价满意度',
  `customer_feedback` varchar(100) DEFAULT NULL COMMENT '客户反馈',
  `customer_state` bigint(5) DEFAULT '1' COMMENT '客户状态',
  `consumer_times` bigint(10) DEFAULT NULL COMMENT '消费次数',
  `satisfaction_cust_id` bigint(32) DEFAULT NULL COMMENT '客户id',
  `survey_date` date DEFAULT NULL COMMENT '调查时间',
  `quality_satisfaction` varchar(10) DEFAULT NULL COMMENT '质量满意度',
  `service_satisfaction` varchar(10) DEFAULT NULL COMMENT '服务满意度',
  `overall_satisfaction` varchar(10) DEFAULT NULL COMMENT '总体满意度',
 */
	
	private Long satisfaction_id;
	private Long satisfaction_cust_id;
	private Date survey_date;
	private String quality_satisfaction;
	private String service_satisfaction;
	private String overall_satisfaction;
	private String price_satisfaction;
	private String customer_feedback;
	private Long customer_state;
	private Long consumer_times;
	private Customer customer;
	
	@Override
	public String toString() {
		return "CustomerSatisfaction [satisfaction_id=" + satisfaction_id + ", satisfaction_cust_id="
				+ satisfaction_cust_id + ", survey_date=" + survey_date + ", quality_satisfaction="
				+ quality_satisfaction + ", service_satisfaction=" + service_satisfaction + ", overall_satisfaction="
				+ overall_satisfaction + ", price_satisfaction=" + price_satisfaction + ", customer_feedback="
				+ customer_feedback + ", customer_state=" + customer_state + ", consumer_times=" + consumer_times
				+ ", customer=" + customer + "]";
	}

	public Long getSatisfaction_id() {
		return satisfaction_id;
	}

	public void setSatisfaction_id(Long satisfaction_id) {
		this.satisfaction_id = satisfaction_id;
	}

	public Long getSatisfaction_cust_id() {
		return satisfaction_cust_id;
	}

	public void setSatisfaction_cust_id(Long satisfaction_cust_id) {
		this.satisfaction_cust_id = satisfaction_cust_id;
	}

	public Date getSurvey_date() {
		return survey_date;
	}

	public void setSurvey_date(Date survey_date) {
		this.survey_date = survey_date;
	}

	public String getQuality_satisfaction() {
		return quality_satisfaction;
	}

	public void setQuality_satisfaction(String quality_satisfaction) {
		this.quality_satisfaction = quality_satisfaction;
	}

	public String getService_satisfaction() {
		return service_satisfaction;
	}

	public void setService_satisfaction(String service_satisfaction) {
		this.service_satisfaction = service_satisfaction;
	}

	public String getOverall_satisfaction() {
		return overall_satisfaction;
	}

	public void setOverall_satisfaction(String overall_satisfaction) {
		this.overall_satisfaction = overall_satisfaction;
	}

	public String getPrice_satisfaction() {
		return price_satisfaction;
	}

	public void setPrice_satisfaction(String price_satisfaction) {
		this.price_satisfaction = price_satisfaction;
	}

	public String getCustomer_feedback() {
		return customer_feedback;
	}

	public void setCustomer_feedback(String customer_feedback) {
		this.customer_feedback = customer_feedback;
	}

	public Long getCustomer_state() {
		return customer_state;
	}

	public void setCustomer_state(Long customer_state) {
		this.customer_state = customer_state;
	}

	public Long getConsumer_times() {
		return consumer_times;
	}

	public void setConsumer_times(Long consumer_times) {
		this.consumer_times = consumer_times;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public CustomerSatisfaction(Long satisfaction_id, Long satisfaction_cust_id, Date survey_date,
			String quality_satisfaction, String service_satisfaction, String overall_satisfaction,
			String price_satisfaction, String customer_feedback, Long customer_state, Long consumer_times,
			Customer customer) {
		super();
		this.satisfaction_id = satisfaction_id;
		this.satisfaction_cust_id = satisfaction_cust_id;
		this.survey_date = survey_date;
		this.quality_satisfaction = quality_satisfaction;
		this.service_satisfaction = service_satisfaction;
		this.overall_satisfaction = overall_satisfaction;
		this.price_satisfaction = price_satisfaction;
		this.customer_feedback = customer_feedback;
		this.customer_state = customer_state;
		this.consumer_times = consumer_times;
		this.customer = customer;
	}

	public CustomerSatisfaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
