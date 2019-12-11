package com.lc.crm.service;

import org.hibernate.criterion.DetachedCriteria;
import com.lc.crm.domain.CustomerSatisfaction;
import com.lc.crm.domain.PageModel;

/**
 * 客户满意度的业务层接口
 * @author user LC
 *
 */
public interface CustomerSatisfactionService {

	public PageModel pageFind(DetachedCriteria detachedCirteria, int currentPage, int pageSize);

	public void delete(Long satisfaction_id);

	public CustomerSatisfaction findInfo(Long satisfaction_id);

	public CustomerSatisfaction findToEdit(Long satisfaction_id);

	public void update(CustomerSatisfaction customerSatisfaction);

}
