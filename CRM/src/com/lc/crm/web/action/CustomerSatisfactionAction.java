package com.lc.crm.web.action;

import org.hibernate.criterion.DetachedCriteria;

import com.lc.crm.domain.CustomerSatisfaction;
import com.lc.crm.domain.PageModel;
import com.lc.crm.service.CustomerSatisfactionService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 客户满意度的action
 * @author user LC
 *
 */
public class CustomerSatisfactionAction extends ActionSupport implements ModelDriven{

	private CustomerSatisfaction customerSatisfaction = new CustomerSatisfaction();
	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return customerSatisfaction;
	}
	
	//注入业务层
	private CustomerSatisfactionService customerSatisfactionService;
	public void setCustomerSatisfactionService(CustomerSatisfactionService customerSatisfactionService) {
		this.customerSatisfactionService = customerSatisfactionService;
	}
	
	//注入分页参数
	private int currentPage;
	private int pageSize;
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	/**
	 * 分页查询所有的额客户满意度
	 * @return
	 */
	public String pageFind() {
		
		//创建离线条件查询对象
		DetachedCriteria detachedCirteria = DetachedCriteria.forClass(CustomerSatisfaction.class);
		//分页查询
		PageModel pageModel = customerSatisfactionService.pageFind(detachedCirteria,currentPage,pageSize);
	
		//将查询结果保存到值栈中
		ActionContext.getContext().getValueStack().push(pageModel);
		return "findSuccess";
	}

	/**
	 * 删除客户满意度
	 * @return
	 */
	public String delete() {
		System.out.println(customerSatisfaction.getSatisfaction_id());
		customerSatisfactionService.delete(customerSatisfaction.getSatisfaction_id());
		return "deleteSuccess";
	}
	
	/**
	 * 查看客户满意度详情
	 * @return
	 */
	public String findInfo() {
		
		CustomerSatisfaction info = customerSatisfactionService.findInfo(customerSatisfaction.getSatisfaction_id());
		
		//将查询结果加入值栈中
		ActionContext.getContext().getValueStack().push(info);
		return "findInfoSuccess";
	}
	
	/**
	 * 返回list界面
	 * @return
	 */
	public String listUI() {
		return "listUI";
	}
	
	/**
	 * 查询要修改的记录
	 * @return
	 */
	public String findToEdit() {
		
		//查询要修改的记录
		CustomerSatisfaction edit = customerSatisfactionService.findToEdit(customerSatisfaction.getSatisfaction_id());
		ActionContext.getContext().getValueStack().push(edit);
		return "findToEdit";
	}
	
	/**
	 * 提交要修改的内容到数据库
	 * @return
	 */
	public String edit() {
		System.out.println(customerSatisfaction);
		customerSatisfactionService.update(customerSatisfaction);
		return "updateSuccess";
	}
}
