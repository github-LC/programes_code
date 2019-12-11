package com.lc.crm.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.lc.crm.domain.CustomerSatisfaction;
import com.lc.crm.domain.PageModel;

/**
 * 客户满意度的dao层的接口
 * @author user LC
 *
 */
public interface CustomerSatisfactionDao {

	int findTotal(DetachedCriteria detachedCirteria);

	List<CustomerSatisfaction> pageFind(DetachedCriteria detachedCirteria, PageModel pageModel);

	public void delete(CustomerSatisfaction customerSatisfaction);

	public CustomerSatisfaction findById(Long satisfaction_id);

	public void update(CustomerSatisfaction customerSatisfaction);


}
