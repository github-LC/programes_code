package com.lc.crm.service.impl;

import java.util.List;
import com.lc.crm.dao.BaseDictDao;
import com.lc.crm.domain.BaseDict;
import com.lc.crm.service.BaseDictService;

public class BaseDictServiceImpl implements BaseDictService {

	//注入dao层
	private BaseDictDao baseDictDao;
	public void setBaseDictDao(BaseDictDao baseDictDao) {
		this.baseDictDao = baseDictDao;
	}
	@Override
	public List<BaseDict> findByTypeCode(BaseDict baseDict) {
		// TODO Auto-generated method stub
		return baseDictDao.findByTypeCode(baseDict);
	}
}
