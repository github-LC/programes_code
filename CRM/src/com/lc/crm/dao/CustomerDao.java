package com.lc.crm.dao;

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import com.lc.crm.domain.Customer;
import com.lc.crm.domain.PageModel;

/**
 * 客户模块的dao层接口
 * @author user LC
 *
 */
public interface CustomerDao {

	public void add(Customer customer);

	public int findTotal(DetachedCriteria detachedCriteria);

	public List<PageModel> pageFind(DetachedCriteria detachedCriteria, PageModel pageModel);

	public Customer findById(Long l);
	
	public void chooseDelete(Customer customer);

	public void update(Customer customer);
	
	public List<Customer> findAll();
	

}
