package com.lc.crm.dao;

import java.util.List;

import com.lc.crm.domain.BaseDict;

public interface BaseDictDao {

	public List<BaseDict> findByTypeCode(BaseDict baseDict);

}
