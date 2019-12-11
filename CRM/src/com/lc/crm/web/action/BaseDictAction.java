package com.lc.crm.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.lc.crm.domain.BaseDict;
import com.lc.crm.service.BaseDictService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

/**
 * 数据字典的action
 * @author user LC
 *
 */
public class BaseDictAction extends ActionSupport implements ModelDriven{

	private BaseDict baseDict = new BaseDict();
	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return baseDict;
	}

	//注入service
	private BaseDictService baseDictService;
	public void setBaseDictService(BaseDictService baseDictService) {
		this.baseDictService = baseDictService;
	}
	
	/**
	 * 查找数据字典中的内容
	 * @return
	 * @throws IOException 
	 */
	public String findByTypeCode() throws IOException {
		List<BaseDict> list = baseDictService.findByTypeCode(baseDict);
		System.out.println(list);
		
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[]{"dict_sort","dict_enable","dict_memo"});
		//将list数据转换成json格式的数据
		JSONArray json = JSONArray.fromObject(list,config);
		
		//获取response将json中的中文编码改成utf-8
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		//将json数据写到页面
		ServletActionContext.getResponse().getWriter().println(json);
		return null;
	}
}
