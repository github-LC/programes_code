package com.lc.crm.service;

import org.hibernate.criterion.DetachedCriteria;
import com.lc.crm.domain.PageModel;
import com.lc.crm.domain.SaleVisit;

public interface SaleVisitService {

	public PageModel findAll(DetachedCriteria detachedCriterira, int currentPage, int pageSize);

	public void add(SaleVisit saleVisit);

	public void delete(SaleVisit saleVisit);

}
