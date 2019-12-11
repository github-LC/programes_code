package com.lc.p2p.model.vo;

import java.util.List;

/**
 * 封装分页参数
 */
public class PaginatinoVO {

    /**
     * 总记录数
     */
    private Integer pageTotal;
    /**
     * 产品列表
     */
    private List list;
    /**
     * 查询的起始值
     */
    private Integer begin;

    /**
     * 当前页
     */
    private Integer currentPage;
    /**
     * 每页查询的数量
     */
    private Integer pageSize;

    /**
     * 总页数
     */
    private Integer totalPage;


    public PaginatinoVO() {
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Integer getBegin() {
        return begin;
    }

    public void setBegin(Integer begin) {
        this.begin = begin;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {

        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}
