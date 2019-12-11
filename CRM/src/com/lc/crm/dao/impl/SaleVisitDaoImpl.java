package com.lc.crm.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import com.lc.crm.dao.SaleVisitDao;
import com.lc.crm.domain.PageModel;
import com.lc.crm.domain.SaleVisit;
/**
 * 客户拜访的dao层实现
 * @author user LC
 *
 */
public class SaleVisitDaoImpl extends HibernateDaoSupport implements SaleVisitDao {

	public int findCount(DetachedCriteria detachedCriterira) {
		// TODO Auto-generated method stub
		detachedCriterira.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriterira);
		if(list!=null) {
			return list.get(0).intValue();
		}
		return 0;
	}

	/**
	 * 分页查询所有
	 */
	@Override
	public List<PageModel> findAll(DetachedCriteria detachedCriterira, PageModel pageModel) {
		// TODO Auto-generated method stub
		return (List<PageModel>) this.getHibernateTemplate().findByCriteria(detachedCriterira, pageModel.getBegin(),pageModel.getPageSize());
	}

	@Override
	public void add(SaleVisit saleVisit) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(saleVisit);
	}

	@Override
	public void delete(SaleVisit saleVisit) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(saleVisit);
	}

}
