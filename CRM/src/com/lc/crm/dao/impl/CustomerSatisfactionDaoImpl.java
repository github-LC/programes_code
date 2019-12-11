package com.lc.crm.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import com.lc.crm.dao.CustomerSatisfactionDao;
import com.lc.crm.domain.CustomerSatisfaction;
import com.lc.crm.domain.PageModel;
/**
 * 客户满意度的dao层的实现
 * @author user LC
 *
 */
public class CustomerSatisfactionDaoImpl extends HibernateDaoSupport implements CustomerSatisfactionDao {

	/**
	 * 查询总记录数
	 */
	@Override
	public int findTotal(DetachedCriteria detachedCirteria) {
		// TODO Auto-generated method stu
		//设置离线条件
		detachedCirteria.setProjection(Projections.rowCount());
		List<Long> count = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCirteria);
		return count.get(0).intValue();
	}

	/**
	 * 分页查询客户满意度
	 */
	@Override
	public List<CustomerSatisfaction> pageFind(DetachedCriteria detachedCirteria, PageModel pageModel) {
		// TODO Auto-generated method stub
		return (List<CustomerSatisfaction>) this.getHibernateTemplate().findByCriteria(detachedCirteria,pageModel.getBegin(),pageModel.getPageSize());
	}

	/**
	 * 删除客户满意度
	 */
	@Override
	public void delete(CustomerSatisfaction customerSatisfaction) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(customerSatisfaction);
	}

	/**
	 * 根据id查询
	 */
	@Override
	public CustomerSatisfaction findById(Long satisfaction_id) {
		// TODO Auto-generated method stub
		List<CustomerSatisfaction> list = (List<CustomerSatisfaction>) this.getHibernateTemplate().find("from CustomerSatisfaction where satisfaction_id=?",satisfaction_id);
		return list.get(0);
	}

	/**
	 * 更新客户满意度
	 */
	@Override
	public void update(CustomerSatisfaction customerSatisfaction) {
		// TODO Auto-generated method stub
		System.out.println(customerSatisfaction.getSatisfaction_cust_id()+"==================");
		this.getHibernateTemplate().update(customerSatisfaction);;
	}

}
