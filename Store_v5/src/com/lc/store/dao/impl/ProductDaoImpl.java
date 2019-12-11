package com.lc.store.dao.impl;

import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import com.lc.store.dao.ProductDao;
import com.lc.store.domain.PageModel;
import com.lc.store.domain.Product;
import com.lc.store.utils.JDBCUtils;

/**
 * 商品信息查询
 * @author user LC
 *
 */
public class ProductDaoImpl implements ProductDao {

	/**
	 * 添加商品
	 */
	@Override
	public void addProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		String sql = "insert into product values(?,?,?,?,?,?,?,?,?,?)";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		qr.update(sql,product.getPid(),product.getPname(),product.getMarket_price(),product.getShop_price(),product.getPimage(),product.getPdate(),product.getIs_hot(),product.getPdesc(),product.getPflag(),product.getCid());
	}

	/**
	 * 后台查询商品数量
	 */
	@Override
	public int queryNumber() throws Exception {
		// TODO Auto-generated method stub
		
		String sql = "select count(*) from product";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return ((Long)qr.query(sql,new ScalarHandler())).intValue();
	}

	/**
	 * 后台查询所有商品
	 */
	@Override
	public List<Product> queryAll(PageModel pageModel) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from product order by pdate desc limit ?,?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		List<Product> list = qr.query(sql,new BeanListHandler<Product>(Product.class),pageModel.getBeginPage(),pageModel.getEveryPageNum());
		return list;
	}

	/**
	 * 查询最热商品
	 * 查询条件：商品还没有上架，商品是最热的，商品按照时间降序排列保证添加上的商品都能取到
	 */
	@Override
	public List<Product> product_hots() throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from product where is_hot=1 and pflag=0 order by pdate desc limit 0,9";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql,new BeanListHandler<Product>(Product.class));
	}

	/**
	 * 查询最新商品
	 * 查询条件：商品还没有上架，商品按照时间降序排列保证添加上的商品都能取到
	 */
	@Override
	public List<Product> product_news() throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from product where  pflag=0 order by pdate desc limit 0,9";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql,new BeanListHandler<Product>(Product.class));
	}

	/**
	 * 查询该类别下所有的商品
	 */
	@Override
	public List<Product> findCategoryProducts(String cid,PageModel pageModel) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from product where cid=? limit ?,?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		List<Product> list = qr.query(sql,new BeanListHandler<Product>(Product.class),cid,pageModel.getBeginPage(),pageModel.getEveryPageNum());
		return list;
	}

	/**
	 * 查询该类别下的所有商品的数量
	 */
	@Override
	public long queryNumber(String cid) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select count(*) from product where cid=?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return (long)qr.query(sql,new ScalarHandler(),cid);
	}

	/**
	 * 查询商品的详细信息
	 */
	@Override
	public Product product_info(int id) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from product where pid=?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql,new BeanHandler<Product>(Product.class),id);
	}

}
