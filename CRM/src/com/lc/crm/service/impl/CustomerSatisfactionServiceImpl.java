package com.lc.crm.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.lc.crm.dao.CustomerSatisfactionDao;
import com.lc.crm.domain.CustomerSatisfaction;
import com.lc.crm.domain.PageModel;
import com.lc.crm.service.CustomerSatisfactionService;
/**
 * 客户满意度的业务层的实现
 * @author user LC
 *
 */
@Transactional
public class CustomerSatisfactionServiceImpl implements CustomerSatisfactionService {

	//注入dao层
	private CustomerSatisfactionDao customerSatisfactionDao;
	public void setCustomerSatisfactionDao(CustomerSatisfactionDao customerSatisfactionDao) {
		this.customerSatisfactionDao = customerSatisfactionDao;
	}
	
	/**
	 * 分页查询所有的客户满意度
	 */
	@Override
	public PageModel pageFind(DetachedCriteria detachedCirteria, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		//创建分页对象模型并封装分页参数
		PageModel pageModel = new PageModel();
		pageModel.setCurrentPage(currentPage);
		pageModel.setPageSize(3);
		
		//查询总记录数
		int totalCount = customerSatisfactionDao.findTotal(detachedCirteria);
		pageModel.setTotalCount(totalCount);
		
		//清除离线条件
		detachedCirteria.setProjection(null);
		//分页查询
		List<CustomerSatisfaction> list = customerSatisfactionDao.pageFind(detachedCirteria,pageModel);
		pageModel.setList(list);
		
		return pageModel;
	}

	/**
	 * 删除客户满意度
	 */
	@Override
	public void delete(Long satisfaction_id) {
		// TODO Auto-generated method stub
		//先查询再删除
		CustomerSatisfaction customerSatisfaction = customerSatisfactionDao.findById(satisfaction_id);
		customerSatisfactionDao.delete(customerSatisfaction);
	}

	/**
	 * 查看客户满意度的详情
	 */
	@Override
	public CustomerSatisfaction findInfo(Long satisfaction_id) {
		// TODO Auto-generated method stub
		return customerSatisfactionDao.findById(satisfaction_id);
	}

	/**
	 * 查询要修改的记录
	 */
	@Override
	public CustomerSatisfaction findToEdit(Long satisfaction_id) {
		// TODO Auto-generated method stub
		return customerSatisfactionDao.findById(satisfaction_id);
	}

	/**
	 * 更新客户满意度
	 */
	@Override
	public void update(CustomerSatisfaction customerSatisfaction) {
		// TODO Auto-generated method stub
		customerSatisfactionDao.update(customerSatisfaction);
	}
	
}
