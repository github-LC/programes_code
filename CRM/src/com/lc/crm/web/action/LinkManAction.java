package com.lc.crm.web.action;

import com.lc.crm.domain.LinkMan;
import com.lc.crm.service.CustomerService;
import com.lc.crm.service.LinkManService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 联系人的action
 * @author user LC
 *
 */
public class LinkManAction extends ActionSupport implements ModelDriven{

	//注入属性
	private LinkMan linkMan = new LinkMan();
	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return linkMan;
	}
	
	//注入业务层
	private LinkManService linkManService;
	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	

}
