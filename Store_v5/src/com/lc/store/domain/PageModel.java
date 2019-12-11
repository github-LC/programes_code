package com.lc.store.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页模型
 * 放置分页参数
 * 关联url
 * @author user LC
 *
 */
public class PageModel {

	private String productType;
	private int currentPage;//当前页
	private int totalPage;//总页数
	private int everyPageNum;//每页的数量
	private int totalNum;//总数量
	private int beginPage;//起始页
	private String url;
	private List list = new ArrayList();
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPage() {
		int num = totalNum%everyPageNum;
		if(num>0) {
			return totalNum/everyPageNum+1;
		}
		return totalNum/everyPageNum;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getEveryPageNum() {
		return everyPageNum;
	}
	public void setEveryPageNum(int everyPageNum) {
		this.everyPageNum = everyPageNum;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public int getBeginPage() {
		beginPage = (currentPage-1)*everyPageNum;
		return beginPage;
	}
	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	
	
	public PageModel() {
		
	}
	
	@Override
	public String toString() {
		return "PageModel [productType=" + productType + ", currentPage=" + currentPage + ", totalPage=" + totalPage
				+ ", everyPageNum=" + everyPageNum + ", totalNum=" + totalNum + ", beginPage=" + beginPage + ", url="
				+ url + ", list=" + list + "]";
	}
	
	public PageModel(String productType, int currentPage, int totalPage, int everyPageNum, int totalNum, int beginPage,
			String url, List list) {
		super();
		this.productType = productType;
		this.currentPage = currentPage;
		this.totalPage = totalPage;
		this.everyPageNum = everyPageNum;
		this.totalNum = totalNum;
		this.beginPage = beginPage;
		this.url = url;
		this.list = list;
	}
	
	
}
