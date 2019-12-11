package com.lc.p2p.web;

import com.lc.p2p.common.constants.Constants;
import com.lc.p2p.model.user.FinanceAccount;
import com.lc.p2p.model.user.User;
import com.lc.p2p.service.loan.BidInfoService;
import com.lc.p2p.service.loan.LoanInfoService;
import com.lc.p2p.service.user.FinanceAccountService;
import com.lc.p2p.service.user.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 用户登陆处理
 */
@Controller
public class LoginController {

    @Autowired
    private LoanInfoService loanInfoService;
    @Autowired
    private UserService userService;
    @Autowired
    private BidInfoService bidInfoService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private FinanceAccountService financeAccountService;

    /**
     * 跳转到登陆界面
     * @return
     */
    @RequestMapping(value="/login")
    public String login(HttpServletRequest request, Model model){

        //从redis中获取数据
        //获取平台平均历史年化收益率：平台所有理财产品的利率的平均值
        model.addAttribute(Constants.HISTORY_AVERAGE_RATE,redisTemplate.opsForValue().get(Constants.HISTORY_AVERAGE_RATE));
        //获取注册总人数
        model.addAttribute(Constants.ALL_USER_COUNT,redisTemplate.opsForValue().get(Constants.ALL_USER_COUNT));
        //查询总投资金额
        model.addAttribute(Constants.ALL_BID_MONEY,redisTemplate.opsForValue().get(Constants.ALL_BID_MONEY));

        return "login";
    }

    @RequestMapping(value="loan/login")
    public @ResponseBody Object login(HttpServletRequest request,
                                      @RequestParam(value="phone",required = true)String phone,
                                      @RequestParam(value="loginPassword",required = true)String loginPassword,
                                      @RequestParam(value="captcha",required=true)String captcha){

        Map<String,Object> resultMap = new HashMap<String,Object>();

        //验证手机号
        if("" == phone){
            resultMap.put(Constants.ERROR_MESSAGE,"请输入手机号");
            return resultMap;
        }else if(!Pattern.matches("^1[1-9]\\d{9}$",phone)){
            resultMap.put(Constants.ERROR_MESSAGE,"请输入正确的手机号");
            return resultMap;
        }


        //验证密码
        if("" == loginPassword){
            resultMap.put(Constants.ERROR_MESSAGE,"请输入密码");
            return resultMap;
        }else if(!Pattern.matches("^(([a-zA-Z]+[0-9]+)|([0-9]+[a-zA-Z]+))[a-zA-Z0-9]*",loginPassword)){
            resultMap.put(Constants.ERROR_MESSAGE,"请输入正确的密码");
            return resultMap;
        }

        //验证图片验证码
        //获取session中的随机验证码
        String relCaptcha = (String) request.getSession().getAttribute(Constants.CAPTCHA);
        if(!StringUtils.equalsIgnoreCase(captcha,relCaptcha)){
            resultMap.put(Constants.ERROR_MESSAGE,"验证码输入错误");
            return resultMap;
        }

        //去数据库查询该用户是否存在
        User user = userService.userLogin(phone,loginPassword);
        if(null == user){
            resultMap.put(Constants.ERROR_MESSAGE,"手机号或密码输入错误，请重新输入");
            return resultMap;
        }
        //更新session中的用户信息
        request.getSession().setAttribute(Constants.SESSION_USER,user);
        resultMap.put(Constants.ERROR_MESSAGE,"OK");

        return resultMap;
    }

    /**
     * 查询用户余额
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value="loan/financeAccount")
    public @ResponseBody  Object balance(HttpServletRequest request,Model model){

        Map<String,Object> resultMap = new HashMap<String,Object>();
        //查询用户余额
        User user = (User) request.getSession().getAttribute(Constants.SESSION_USER);
        FinanceAccount financeAccount = financeAccountService.queryUserBalanceById(user.getId());
        if (null != financeAccount) {
            resultMap.put(Constants.USER_BALANCE,financeAccount.getAvailableMoney());
        }

        return resultMap;
    }


    @RequestMapping(value="loan/loadStat")
    public @ResponseBody Object loadSta(){

        Map<String,Object> resultMap = new HashMap<String,Object>();

        //查询历史年化收益率
        resultMap.put(Constants.HISTORY_AVERAGE_RATE,loanInfoService.queryHistoryAverageRate());
        //查询平台用户数量
        resultMap.put(Constants.ALL_BID_MONEY,bidInfoService.queryAllBidMoney());
        //查询成交金额
        resultMap.put(Constants.ALL_USER_COUNT,userService.queryAllUserCount());

        return resultMap;
    }


    /**
     * 用户退出功能
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value="loan/logout")
    public String logout(HttpServletRequest request,Model model){

        //清空session中的用户信息
        request.getSession().invalidate();

        //重定向到首页
        return "redirect:/index";
    }

}
