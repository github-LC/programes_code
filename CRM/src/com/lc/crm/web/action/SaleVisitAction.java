package com.lc.crm.web.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.lc.crm.domain.Customer;
import com.lc.crm.domain.PageModel;
import com.lc.crm.domain.SaleVisit;
import com.lc.crm.domain.User;
import com.lc.crm.service.CustomerService;
import com.lc.crm.service.SaleVisitService;
import com.lc.crm.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

/**
 * 顾客拜访记录的实体
 * @author user LC
 *
 */
public class SaleVisitAction extends ActionSupport implements ModelDriven{

	//创建模型驱动
	private SaleVisit saleVisit = new SaleVisit();
	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return saleVisit;
	}
	
	//注入业务层
	@Resource(name="saleVisitService")
	private SaleVisitService saleVisitService;
	@Resource(name="customerService")
	private CustomerService customerService;
	@Resource(name="userService")
	private UserService userService;
	
	//提供分页参数
	private int currentPage;
	private int pageSize;
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 查询所有记录带分页
	 * @return
	 */
	public String findAll() {
		//设置离线查询
		DetachedCriteria detachedCriterira = DetachedCriteria.forClass(SaleVisit.class);
		PageModel pageModel = saleVisitService.findAll(detachedCriterira,currentPage,pageSize);
		System.out.println(pageModel);
		
		//将查询结果保存到值栈中
		ActionContext.getContext().getValueStack().push(pageModel);
		System.out.println("执行到这里");
		return "findAll";
	}

	/**
	 * 跳转到添加页面
	 * @return
	 */
	public String addUI() {
		
		return "addUI";
		
	}
	
	/**
	 * 异步加载客户
	 * @return
	 * @throws IOException
	 */
	public String findAllCustomer() throws IOException {
		List<Customer> list = customerService.findAll();
		
		//将多余内容去掉
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[] {"sourceBaseDict","industryBaseDict","levelBaseDict","cust_phone","cust_mobile","cust_user_id","cust_create_id","cust_filepath","linkMan"});
		JSONArray json = JSONArray.fromObject(list, config);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(json);
		return null;
	}
	
	/**
	 * 异步加载用户
	 * @return
	 * @throws IOException
	 */
	public String findAllUser() throws IOException {
		List<User> list = userService.findAll();
		
		//将多余内容去掉
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[] {"user_code","user_password","user_state","user_email"});
		JSONArray json = JSONArray.fromObject(list, config);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(json);
		return null;
	}
	
	/**
	 * 添加拜访记录
	 * @return
	 */
	public String add() {
		System.out.println(saleVisit);
		saleVisitService.add(saleVisit);
		return "addSuccess";
	}
	
	/**
	 * 删除拜访记录
	 * @return
	 */
	public String delete() {
		saleVisitService.delete(saleVisit);
		return "deleteSuccess";
	}
	
	
	/**
	 * 条件查询
	 * @return
	 */
	public String detachedCriteriaFind() {
		
		//创建离线条件查询的对象
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SaleVisit.class);
		if(saleVisit.getCustomer().getCust_name()!=null && !saleVisit.getCustomer().getCust_name().equals("")) {
			//先在顾客表中按照名字进行模糊查询
			detachedCriteria.add(Restrictions.like("cust_name","%"+saleVisit.getCustomer().getCust_name()+"%"));
			PageModel customer = customerService.pageFind(detachedCriteria, currentPage, pageSize);
			System.out.println(customer);
			//然后将按名字查询的顾客在拜访记录表中进行查询
			List<Customer> list = customer.getList();
			for(Customer c:list) {
				detachedCriteria.add(Restrictions.eq("visit_cust_id",c.getCust_id()));
			}
		}
		
		if(saleVisit.getUser().getUser_id()!=null && saleVisit.getUser()!=null) {
			detachedCriteria.add(Restrictions.eq("user.user_id",saleVisit.getUser().getUser_id()));
		}
		
		PageModel pageModel= saleVisitService.findAll(detachedCriteria,currentPage,pageSize);
		ActionContext.getContext().getValueStack().push(pageModel);
		return "findAll";
	}
}
