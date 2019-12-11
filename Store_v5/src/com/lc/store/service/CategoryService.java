package com.lc.store.service;

import java.util.List;

import com.lc.store.domain.Category;

public interface CategoryService {

	public List<Category> allCats() throws Exception;

	public void addCategory(String cname, String cid) throws Exception;

}
