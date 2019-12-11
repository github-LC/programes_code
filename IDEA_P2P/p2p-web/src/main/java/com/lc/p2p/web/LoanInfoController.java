package com.lc.p2p.web;

import com.lc.p2p.common.constants.Constants;
import com.lc.p2p.model.loan.LoanInfo;
import com.lc.p2p.model.user.FinanceAccount;
import com.lc.p2p.model.user.User;
import com.lc.p2p.model.vo.PaginatinoVO;
import com.lc.p2p.model.vo.UserBidTop;
import com.lc.p2p.service.loan.BidInfoService;
import com.lc.p2p.service.loan.LoanInfoService;
import com.lc.p2p.service.user.FinanceAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 处理产品信息的控制层
 */
@Controller
public class LoanInfoController {

    @Autowired
    private LoanInfoService loanInfoService;
    @Autowired
    private BidInfoService bidInfoService;
    @Autowired
    private FinanceAccountService financeAccountService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 分页显示产品相关信息
     * 我要投资，投资排行榜
     * @return
     */
    @RequestMapping(value="/loan/loan")
    public String loan(HttpServletRequest request,
                       Model model ,
                       @RequestParam(value="currentPage",required=false) Integer currentPage,
                       @RequestParam(value="ptype",required=false) Integer ptype){


        //判断当前页是否为空
        if (null == currentPage) {
            currentPage = 1;
        }


        //分类分页查询产品的信息列表
        //判断产品类型是否为空
        //设置查询类型和分页参数

        if (null != ptype) {

            PaginatinoVO paginatinoVO =  loanInfoService.queryLoanInfoByPage(currentPage,ptype);
            model.addAttribute("loanInfoList",paginatinoVO.getList());
            model.addAttribute("totalPage",paginatinoVO.getTotalPage());
            model.addAttribute("totalRows",paginatinoVO.getPageTotal());
            model.addAttribute("currentPage",paginatinoVO.getCurrentPage());

            if (null != ptype) {
                model.addAttribute("ptype",ptype);
            }
        }

        //投资排行榜
        //从redis中获取用户投资
        List<UserBidTop> list = bidInfoService.queryUserBidTop();
        model.addAttribute("topUserList",list);

        return "loan";
    }

    /**
     * 查询产品的信息和产品对应的投资记录(展示用户手机号并做脱敏处理)
     *
     * @return
     */
    @RequestMapping(value = "/loan/loanInfo")
    public String loanInfo(HttpServletRequest request,
                           Model model,
                           @RequestParam(value="loanId",required=true) Integer loanId,
                           @RequestParam(value="currentPage",required = false) Integer currentPage) {

        if (null == currentPage) {
            currentPage = 1;
        }
        //根据产品id查询产品信息
        LoanInfo loanInfo = loanInfoService.queryLoanInfoById(loanId);
        model.addAttribute("loanInfo",loanInfo);

        //查询产品的投资记录
        PaginatinoVO paginatinoVO = bidInfoService.queryBidInfoListByLoanId(loanId,currentPage);
        model.addAttribute("bidInfoList",paginatinoVO.getList());
        model.addAttribute("totalPage",paginatinoVO.getTotalPage());
        model.addAttribute("totalRows",paginatinoVO.getPageTotal());
        model.addAttribute("currentPage",paginatinoVO.getCurrentPage());
        model.addAttribute("loanId",loanId);

        //查询用户余额
        User user = (User) request.getSession().getAttribute(Constants.SESSION_USER);
        if(null != user){
            FinanceAccount financeAccount = financeAccountService.queryUserBalanceById(user.getId());
            model.addAttribute(Constants.USER_BALANCE,financeAccount.getAvailableMoney());
        }
        model.addAttribute(Constants.SESSION_USER,user);

        return "loanInfo";
    }

    /**
     * 我的中心
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value="loan/myCenter")
    public String myCenter(HttpServletRequest request,Model model){
        //从RedisTemplate中获取账户信息
        FinanceAccount financeAccount = (FinanceAccount) redisTemplate.opsForValue().get(Constants.FINANCEACCOUNT);
        model.addAttribute(Constants.FINANCEACCOUNT,financeAccount);
        return "myCenter";
    }

 }
