package com.lc.p2p.service.impl.loan;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import com.lc.p2p.common.constants.Constants;
import com.lc.p2p.mapper.loan.BidInfoMapper;
import com.lc.p2p.mapper.loan.LoanInfoMapper;
import com.lc.p2p.mapper.user.FinanceAccountMapper;
import com.lc.p2p.model.loan.BidInfo;
import com.lc.p2p.model.loan.LoanInfo;
import com.lc.p2p.model.vo.PaginatinoVO;
import com.lc.p2p.model.vo.ResultObject;
import com.lc.p2p.service.loan.LoanInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 产品业务接口实现类
 * ClassName:LoanInfoServiceImpl
 * <p>Company:www.bjpowernode.com</p>
 * <p>Description:</p>
 */
@Service("LoanInfoService")
public class LoanInfoServiceImpl implements LoanInfoService {

	@Autowired
	private RedisTemplate<Object,Object> redisTemplate;
	@Autowired
	private LoanInfoMapper loanInfoMapper;
	@Autowired
	private FinanceAccountMapper financeAccountMapper;
	@Autowired
	private BidInfoMapper bidInfoMapper;

	/**
	 * 查询平台历史人均利益率
	 * @return
	 */
	public Double queryHistoryAverageRate() {

		//从redis中获取指定key的操作对象
		BoundValueOperations boundValueOperations  = redisTemplate.boundValueOps(Constants.HISTORY_AVERAGE_RATE);
		Double historyAverageRate = (Double) boundValueOperations.get();

		//判断redis中是否有值，如果没有就将从数据库中查询的值存放到redis中
		if (null == historyAverageRate) {

			//从数据库中查询平台历史人均利益率
			historyAverageRate = loanInfoMapper.selectHistoryAverageRate();
			//存放到redis中
			redisTemplate.opsForValue().set(Constants.HISTORY_AVERAGE_RATE,historyAverageRate,15, TimeUnit.MINUTES);
		}

		return historyAverageRate;
	}

	/**
	 * 查询产品信息列表
	 * @return
	 * @param paramMap
	 */
	@Override
	public List<LoanInfo> queryLoanInfoByProductType(Map<String, Object> paramMap) {

		//创建分页对象
		PaginatinoVO paginatinoVO = new PaginatinoVO();
		//设置当前页
		paginatinoVO.setCurrentPage((Integer) paramMap.get("currentPage"));
		//设置每页查询的数量
		paginatinoVO.setPageSize((Integer) paramMap.get("pageSize"));
		//设置查询的起始下标
		paginatinoVO.setBegin((paginatinoVO.getCurrentPage()-1)*paginatinoVO.getPageSize());

		//设置起始下标
		paramMap.put("begin",paginatinoVO.getBegin());
		//设置查询的数量
		paramMap.put("pageSize",paginatinoVO.getPageSize());

		//查询总记录数

		return loanInfoMapper.selectLoanInfoByPage(paramMap);
	}

	/**
	 * 分页查询产品信息
	 *
	 * @param
	 * @return
	 */
	@Override
	public PaginatinoVO queryLoanInfoByPage(Integer currentPage,Integer ptype) {

		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("productType",ptype);
		paramMap.put("currentPage",currentPage);

		//创建分页对象
		PaginatinoVO paginatinoVO = new PaginatinoVO();
		//设置当前页
        paginatinoVO.setCurrentPage(currentPage);
		//设置每页查询的数量
		paginatinoVO.setPageSize(9);
		//设置查询的起始下标
		paginatinoVO.setBegin((currentPage-1)*paginatinoVO.getPageSize());

		//设置起始下标
		paramMap.put("begin",paginatinoVO.getBegin());
		//设置查询的数量
		paramMap.put("pageSize",paginatinoVO.getPageSize());

		//查询总记录数
		Integer pageTotal = loanInfoMapper.selectPageTotal(paramMap);
		paginatinoVO.setPageTotal(pageTotal);
		//设置总页数
		if(pageTotal%paginatinoVO.getPageSize()==0){
			paginatinoVO.setTotalPage(pageTotal/paginatinoVO.getPageSize());
		}else{
			paginatinoVO.setTotalPage(pageTotal/paginatinoVO.getPageSize()+1);
		}

		//分页查询产品
		List<LoanInfo> loans = loanInfoMapper.selectLoanInfoByPage(paramMap);
		paginatinoVO.setList(loans);

		return paginatinoVO;
	}

	/**
	 * 查询产品详细信息
	 *
	 * @param loanId
	 * @return
	 */
	@Override
	public LoanInfo queryLoanInfoById(Integer loanId) {
		return loanInfoMapper.selectByPrimaryKey(loanId);
	}


}
