package com.lc.crm.web.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.lc.crm.domain.Customer;
import com.lc.crm.domain.PageModel;
import com.lc.crm.service.CustomerService;
import com.lc.crm.utils.UploadUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/**
 * 处理客户的action
 * @author user LC
 *
 */
//加载配置文件
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("calsspath:applicationContext.xml")
public class CustomerAction extends ActionSupport implements ModelDriven{

	private Customer customer = new Customer();
	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return customer;
	}
	
	//注入业务层
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
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
	
	//注入上传参数
	private String uploadFileName;//文件名称
	private File upload;//上传文件本身
	private String uploadContextType;//上传文件类型
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadContextType(String uploadContextType) {
		this.uploadContextType = uploadContextType;
	}
	
	/**
	 * 查找所有的客户
	 * @return
	 */
	/*
	 * public String findAll() {
	 * 
	 * List<Customer> customers = customerService.findAll(); for(Customer customer :
	 * customers) {
	 * 
	 * System.out.println(customer); } if(customers.size()>0) { //将查询到的结果保存在session中
	 * ActionContext.getContext().getSession().put("customers", customers.get(0));
	 * }else { this.addActionError("查询结果为空，没有客户记录！"); return "404"; } return
	 * "findSuccess"; }
	 */

	/**
	 * 跳转到添加的界面
	 * @return
	 */
	public String addUI() {
		
		return "addUI";
	}
	
	/**
	 * 添加用户
	 * @return
	 * @throws IOException 
	 */
	public String add() throws IOException {
		
		if(upload!=null) {
			//获取随机文件名称
			String fileName = UploadUtils.getFileName(uploadFileName);
			System.out.println(fileName);
			//设置文件上传路径
			String path = "D:/upload";
			//获取二级目录
			String uploadPath = UploadUtils.getFilePath(uploadFileName);
			
			//创建目录
			File file = new File(path+uploadPath);
			if(!file.exists()) {
				file.mkdirs();
			}
			
			//文件上传
			File uploadFile = new File(path+uploadPath+"/"+fileName);
			System.out.println(uploadFile);
			FileUtils.copyFile(upload,uploadFile);
			
			//将图片完整路径保存到数据库中
			customer.setCust_filepath(path+uploadPath+"/"+fileName);
		}
		
		//保存客户
		System.out.println(customer); 
		customerService.add(customer);
		//将当前id值传递
		Long id = customer.getCust_id();
		ActionContext.getContext().getValueStack().push(customer);
		return "add";
	}
	
	/**
	 * 分页条件查询
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String pageFind() throws UnsupportedEncodingException {
		
		//创建离线
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
		//在web层设置离线查询条件
		if(customer.getCust_name()!=null) {
			detachedCriteria.add(Restrictions.like("cust_name","%"+customer.getCust_name()+"%"));
		}
		if(customer.getIndustryBaseDict()!=null && customer.getIndustryBaseDict().getDict_id()!=null) {
			detachedCriteria.add(Restrictions.eq("industryBaseDict.dict_id",customer.getIndustryBaseDict().getDict_id()));
		}
		if(customer.getLevelBaseDict()!=null && customer.getLevelBaseDict().getDict_id()!=null ) {
			detachedCriteria.add(Restrictions.eq("levelBaseDict.dict_id",customer.getLevelBaseDict().getDict_id()));
		}
		if(customer.getSourceBaseDict()!=null && customer.getSourceBaseDict().getDict_id()!=null) {
			detachedCriteria.add(Restrictions.eq("sourceBaseDict.dict_id",customer.getSourceBaseDict().getDict_id()));
		}
		if(currentPage==0) {
			currentPage=1;
		}
		//查询
		PageModel pageModel = customerService.pageFind(detachedCriteria,currentPage,pageSize); 
		//将查询结果存到值栈
		ActionContext.getContext().getValueStack().push(pageModel);
		return "findSuccess";
	}
	
	/**
	 * 批量删除数据
	 * @return
	 */
	public String chooseDelete() {
		
		//获取选中要删除的数据
		customerService.chooseDelete(customer.getCust_id());
		return "deleteSucess";
	}
	
	/**
	 * 跳转到修改页面
	 * @return
	 */
	public String updateUI() {
		
		Customer custoemr = customerService.findById(customer.getCust_id());
		if(custoemr!=null) {
			//将顾客信息保存到值栈中
			ActionContext.getContext().getValueStack().push(custoemr);;
		}
		return "updateUI";
	}
	
	/**
	 * 修改客户信息
	 * @return
	 * @throws IOException
	 */
	public String update() throws IOException {
		System.out.println(customer);
		//判断文件是否修改
		if(upload!=null) {
			//若查出图片的路径就删除图片
			if(customer.getCust_filepath()!=null||customer.getCust_filepath().contentEquals("")) {
				
				File oldFile = new File(customer.getCust_filepath());
				
				if(oldFile.exists()) {
					oldFile.delete();
				}
				
			}
			
			//获取上传的文件
			//获取随机文件名称
			String fileName = UploadUtils.getFileName(uploadFileName);
			System.out.println(fileName);
			//设置文件上传路径
			String path = "D:/upload";
			//获取二级目录
			String uploadPath = UploadUtils.getFilePath(uploadFileName);
			
			//创建目录
			File file = new File(path+uploadPath);
			if(!file.exists()) {
				file.mkdirs();
			}
			
			//文件上传
			File uploadFile = new File(path+uploadPath+"/"+fileName);
			System.out.println(uploadFile);
			FileUtils.copyFile(upload,uploadFile);
			
			//将图片完整路径保存到数据库中
			customer.setCust_filepath(path+uploadPath+"/"+fileName);
		}
		
		customerService.update(customer);
		return "updateSucess";
	}
	
}
