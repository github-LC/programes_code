package com.lc.crm.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.lc.crm.domain.PageModel;
import com.lc.crm.domain.SaleVisit;

public interface SaleVisitDao {

	public int findCount(DetachedCriteria detachedCriterira);

	public List<PageModel> findAll(DetachedCriteria detachedCriterira, PageModel pageModel);

	public void add(SaleVisit saleVisit);

	public void delete(SaleVisit saleVisit);

}
