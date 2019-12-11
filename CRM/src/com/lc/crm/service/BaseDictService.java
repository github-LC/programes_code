package com.lc.crm.service;

import java.util.List;

import com.lc.crm.domain.BaseDict;

public interface BaseDictService {

	public List<BaseDict> findByTypeCode(BaseDict baseDict);

}
