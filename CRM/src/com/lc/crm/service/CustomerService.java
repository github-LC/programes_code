package com.lc.crm.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.lc.crm.domain.Customer;
import com.lc.crm.domain.PageModel;

/**
 * 客户模块的接口
 * @author user LC
 *
 */
public interface CustomerService {
	
	public List<Customer> findAll(); 

	public void add(Customer customer);

	public PageModel pageFind(DetachedCriteria detachedCriteria, int currentPage, int pageSize);

	public void chooseDelete(Long cust_id);

	public Customer findById(Long cust_id);

	public void update(Customer customer);

}
