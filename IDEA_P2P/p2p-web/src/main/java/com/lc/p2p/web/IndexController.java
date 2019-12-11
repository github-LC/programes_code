package com.lc.p2p.web;

import com.lc.p2p.common.constants.Constants;
import com.lc.p2p.model.loan.LoanInfo;
import com.lc.p2p.service.loan.BidInfoService;
import com.lc.p2p.service.loan.LoanInfoService;
import com.lc.p2p.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 首页控制层
 */
@Controller
public class IndexController {
	
	@Autowired
	private LoanInfoService loanInfoService;
	@Autowired
    private UserService userService;
	@Autowired
	private BidInfoService bidInfoService;
	
	@RequestMapping(value={"/index","/loan/index"})
	public String index(HttpServletRequest request, Model model) {
		
		//获取平台平均历史年化收益率：平台所有理财产品的利率的平均值
		Double historyAverageRate = loanInfoService.queryHistoryAverageRate();
		//将以上查询结果存放到model对象中
		model.addAttribute(Constants.HISTORY_AVERAGE_RATE,historyAverageRate);

		//获取注册总人数
        Long allUserCount = userService.queryAllUserCount();
        model.addAttribute(Constants.ALL_USER_COUNT,allUserCount);

        //查询总投资金额
		Double allBidMoney = bidInfoService.queryAllBidMoney();
		model.addAttribute(Constants.ALL_BID_MONEY,allBidMoney);

		//查询新手宝产品信息列表
		//用map集合存放产品类型，分页的起始下标，每页查询的数量
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("productType",Constants.PRODUCT_TYPR_X);
		//设置起始下标
		paramMap.put("currentPage",1);
		//设置查询的数量
		paramMap.put("pageSize",1);
		List<LoanInfo> xLoanInfoList = loanInfoService.queryLoanInfoByProductType(paramMap);
		model.addAttribute("xLoanInfoList",xLoanInfoList);

		//查询优选产品信息列表
		paramMap.put("productType",Constants.PRODUCT_TYPE_U);
		//设置起始下标
		paramMap.put("currentPage",1);
		//设置查询的数量
		paramMap.put("pageSize",4);
		List<LoanInfo> uLoanInfoList = loanInfoService.queryLoanInfoByProductType(paramMap);
		model.addAttribute("uLoanInfoList",uLoanInfoList);

		//查询散标产品信息
		paramMap.put("productType",Constants.PRODUCT_TYPE_S);
		//设置起始下标
		paramMap.put("currentPage",1);
		//设置查询的数量
		paramMap.put("pageSize",8);
		List<LoanInfo> sLoanInfoList = loanInfoService.queryLoanInfoByProductType(paramMap);
		model.addAttribute("sLoanInfoList",sLoanInfoList);

		return "index";
	}
}
