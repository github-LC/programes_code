package com.lc.store.web.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.lc.store.domain.Category;
import com.lc.store.domain.PageModel;
import com.lc.store.domain.Product;
import com.lc.store.service.CategoryService;
import com.lc.store.service.ProductService;
import com.lc.store.service.impl.CategoryServiceImpl;
import com.lc.store.service.impl.ProductServiceImpl;
import com.lc.store.utils.UUIDUtils;
import com.lc.store.utils.UploadUtils;
import com.lc.store.web.base.BaseServlet;
/**
 * 实现后台商品管理
 * @author user LC
 *
 */
public class AdminProductServlet extends BaseServlet {

	/**
	 * 分页查询所有的商品
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public String findProductsWithPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//获取当前页
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		//调用业务层和dao层查询所有商品
		ProductService service = new ProductServiceImpl();
		PageModel pageModel = service.findProductsWithPage(currentPage);
		
		request.setAttribute("page",pageModel);
		return "/admin/product/list.jsp";
		
	}
	
	/**
	 * 向后台添加商品页面跳转，跳转的同时查询分类商品并保存在request中发送
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String addProductUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//查询商品的分类并跳转到添加页面显示
		CategoryService service = new CategoryServiceImpl();
		List<Category> list = service.allCats();
		request.setAttribute("allCats", list);
		return "/admin/product/add.jsp";
		
	}
	
	public String addProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//创建存储数据的集合
		Map<String,String> map = new HashMap<String,String>();
		//获取客户端上传的商品信息，由于是上传操作所以用request.getParameter不能获取到
		DiskFileItemFactory dif = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(dif);
		List<FileItem> list = sfu.parseRequest(request);
		
		//遍历判断是否是普通项
		for(FileItem fileItem : list) {
			if(fileItem.isFormField()) {
				map.put(fileItem.getFieldName(),fileItem.getString("utf-8"));
			}else {
				
				//获取上传图像的后缀名
				String oldFileName = fileItem.getName();
				String newFileName = UploadUtils.getUUIDName(oldFileName);
				
				//将输入流和输出流关联
				InputStream is = fileItem.getInputStream();
				
				String realPath = getServletContext().getRealPath("/products/3/");
				String dir = UploadUtils.getDir(newFileName);
				String path = realPath+dir;
				
				//在内存中创建该目录
				File newDir = new File(path);
				if(!newDir.exists()) {
					newDir.mkdirs();
				}
				
				//在服务器端创建该后缀的空文件
				File finalFile = new File(newDir,newFileName);
				if(!finalFile.exists()) {
					finalFile.createNewFile();
				}
				OutputStream os = new FileOutputStream(finalFile);
				IOUtils.copy(is, os);
				IOUtils.closeQuietly(is);
				IOUtils.closeQuietly(os);
				
				//将读取到的数据放入集合中
				map.put("pimage","products/3"+dir+"/"+newFileName);
				System.out.println(path);
			}
		}
		//将数据填充到对象中
		Product product = new Product();
		BeanUtils.populate(product,map);
		product.setPflag(0);
		product.setPdate(new Date());
		product.setPid(UUIDUtils.getId());
		
		ProductService service = new ProductServiceImpl();
		service.addProduct(product);
		
		//这里要跳转的方法在当前程序中，所以不能用请求转发，应该用重定向
		response.sendRedirect("AdminProductServlet?method=findProductsWithPage&currentPage=1");
		return null;
		
	}
}
