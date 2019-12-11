package com.lc.crm.dao.impl;

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import com.lc.crm.dao.CustomerDao;
import com.lc.crm.domain.Customer;
import com.lc.crm.domain.PageModel;
/**
 * 客户模块的dao层的实现
 * @author user LC
 *
 */
public class CustomerDaoImpl  extends HibernateDaoSupport implements CustomerDao{

	/**
	 * 实现查询所有的客户
	 */
	 @Override public List<Customer> findAll() { 
		 // TODO Auto-generated method stub
		 List<Customer> customers=(List<Customer>)this.getHibernateTemplate().find("from Customer"); 
		 return customers; 
	 }
	 
	/**
	 * 添加客户
	 */
	@Override
	public void add(Customer customer) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(customer);
	}

	/**
	 * 查询总的记录数
	 */
	 @Override 
	 public int findTotal(DetachedCriteria detachedCriteria) { 
		 // TODOAuto-generated method stub 
		 //添加聚合函数添加查询
	     detachedCriteria.setProjection(Projections.rowCount()); 
	     List<Long> list =(List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
	     if(list!=null) { 
		   return list.get(0).intValue(); 
	     } 
	    return 0; 
	 }

	/**
	 * 分页查询
	 */
	 @Override 
	 public List<PageModel> pageFind(DetachedCriteria detachedCriteria, PageModel pageModel) { 
		 // TODO Auto-generated method stub 
		 return (List<PageModel>)this.getHibernateTemplate().findByCriteria(detachedCriteria,pageModel.getBegin(),pageModel.getPageSize());
	 }
	
	public Customer findById(Long cust_id) { 
		return (Customer)this.getHibernateTemplate().find("from Customer where cust_id=?",cust_id).get(0);
	}
	
	/**
	 * 删除客户
	 */
	@Override
	public void chooseDelete(Customer customer) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(customer);;
	}

	/**
	 * 更新客户数据
	 */
	 @Override public void update(Customer customer) { 
		 // TODO Auto-generated method stub 
		 this.getHibernateTemplate().update(customer); 
	  }

}
