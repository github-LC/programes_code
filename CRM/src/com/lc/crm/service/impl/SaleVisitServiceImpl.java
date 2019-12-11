package com.lc.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;
import com.lc.crm.dao.SaleVisitDao;
import com.lc.crm.domain.PageModel;
import com.lc.crm.domain.SaleVisit;
import com.lc.crm.service.SaleVisitService;
/**
 * 客户拜访的业务层的实现
 * @author user LC
 *
 */
@Transactional
public class SaleVisitServiceImpl implements SaleVisitService {

	//注入dao层
	@Resource(name="saleVisitDao")
	private SaleVisitDao saleVisitDao;

	/**
	 * 分页查询所有的拜访记录
	 */
	@Override
	public PageModel findAll(DetachedCriteria detachedCriterira, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		PageModel pageModel = new PageModel();
		pageModel.setCurrentPage(currentPage);
		pageModel.setPageSize(3);
		
		//查询总记录数量
		int count = saleVisitDao.findCount(detachedCriterira);
		pageModel.setTotalCount(count);
		
		//清空上次离线查询设置的条件
		detachedCriterira.setProjection(null);
		//查询所有的记录
		List<PageModel> list = saleVisitDao.findAll(detachedCriterira,pageModel);
		pageModel.setList(list);
		return pageModel;
	}

	/**
	 * 增加拜访记录
	 */
	@Override
	public void add(SaleVisit saleVisit) {
		// TODO Auto-generated method stub
		saleVisitDao.add(saleVisit);
	}

	/**
	 * 删除拜访记录
	 */
	@Override
	public void delete(SaleVisit saleVisit) {
		// TODO Auto-generated method stub
		saleVisitDao.delete(saleVisit);
	}
}
