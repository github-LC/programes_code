package com.lc.crm.service.impl;

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;
import com.lc.crm.dao.CustomerDao;
import com.lc.crm.domain.Customer;
import com.lc.crm.domain.PageModel;
import com.lc.crm.service.CustomerService;
/**
 * 客户模块的接口的实现
 * @author user LC
 *
 */
@Transactional
public class CustomerServiceImpl implements CustomerService {

	//注入dao层
	private CustomerDao customerDao;
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	/**
	 * 查找所有的客户
	 */
	 @Override 
	 public List<Customer> findAll() { 
		 // TODO Auto-generated methodstub
		 List<Customer> customers = customerDao.findAll(); 
		 return customers; 
	 }
	 
	/**
	 * 添加客户
	 */
	@Override
	public void add(Customer customer) {
		// TODO Auto-generated method stub
		customerDao.add(customer);
	}

	/**
	 * 分页查询
	 */
	@Override
	public PageModel pageFind(DetachedCriteria detachedCriteria, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		
		//设置分页参数
		//获取总记录数
		PageModel pageModel = new PageModel();
		int count = customerDao.findTotal(detachedCriteria);
		pageModel.setTotalCount(count);
		pageModel.setCurrentPage(currentPage);
		pageModel.setPageSize(5);
		
		//进行分页查询
		//清除离线查询的条件
		detachedCriteria.setProjection(null);
		List<PageModel> list = customerDao.pageFind(detachedCriteria,pageModel);
		pageModel.setList(list);
		return pageModel;
	}

	/**
	 * 批量删除客户
	 */
	@Override
	public void chooseDelete(Long cust_id) {
		// TODO Auto-generated method stub
		Customer customer = (Customer) customerDao.findById(cust_id);
		customerDao.chooseDelete(customer);
	}

	/**
	 * 根据id查询顾客
	 */
	@Override
	public Customer findById(Long cust_id) {
		// TODO Auto-generated method stub
		return (Customer) customerDao.findById(cust_id);
	}

	/**
	 * 更新客户数据
	 */
	@Override
	public void update(Customer customer) {
		// TODO Auto-generated method stub
		customerDao.update(customer);
	}
}
