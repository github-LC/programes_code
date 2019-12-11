package com.lc.crm.domain;

import java.util.List;

/**
 * 分页参数载体
 * @author user LC
 * @param <T>
 *
 */
public class PageModel<T> {

	private int currentPage; //当前页
	private int begin; //每页起始值
	private int totalCount; //总记录
	private int pageTotal; //总页数
	private int pageSize; //每页的数量
	private List<T> list;
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getBegin() {
		return (currentPage-1)*pageSize;
	}
	public void setBegin(int begin) {
		this.begin = begin;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageTotal() {
		if(totalCount%pageSize==0){
			return totalCount/pageSize;
		}else {
			
			return totalCount/pageSize+1;
		}
	}
	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
	public PageModel(int currentPage, int begin, int totalCount, int pageTotal, int pageSize, List<T> list) {
		super();
		this.currentPage = currentPage;
		this.begin = begin;
		this.totalCount = totalCount;
		this.pageTotal = pageTotal;
		this.pageSize = pageSize;
		this.list = list;
	}
	
	public PageModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "PageModel [currentPage=" + currentPage + ", begin=" + begin + ", totalCount=" + totalCount
				+ ", pageTotal=" + pageTotal + ", pageSize=" + pageSize + ", list=" + list + "]";
	}
	
}
