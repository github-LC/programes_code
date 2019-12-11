package com.lc.crm.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.lc.crm.dao.BaseDictDao;
import com.lc.crm.domain.BaseDict;

public class BaseDictDaoImpl extends HibernateDaoSupport implements BaseDictDao{

	/**
	 * 查询顾客行业来源，级别
	 */
	public List<BaseDict> findByTypeCode(BaseDict baseDict) {
		// TODO Auto-generated method stub
		return (List<BaseDict>) this.getHibernateTemplate().find("from BaseDict where dict_type_code=?",baseDict.getDict_type_code());
	}
	
}
