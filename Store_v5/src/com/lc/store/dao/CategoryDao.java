package com.lc.store.dao;

import java.util.List;

import com.lc.store.domain.Category;

public interface CategoryDao {

	public List<Category> allCats() throws Exception;

	public void addCategory(String cname, String cid) throws Exception;

}
