package com.lc.crm.service.impl;

import com.lc.crm.dao.LinkManDao;
import com.lc.crm.service.LinkManService;
/**
 * 联系人模块的功能实现
 * @author user LC
 *
 */
public class LinkManServiceImpl implements LinkManService {

	//注入dao层
	private LinkManDao linkManDao;
	public void setLinkManDao(LinkManDao linkManDao) {
		this.linkManDao = linkManDao;
	}
	
}
