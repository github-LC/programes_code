package com.lc.p2p.web;

import com.alibaba.druid.util.StringUtils;
import com.lc.p2p.common.constants.Constants;
import com.lc.p2p.model.user.User;
import com.lc.p2p.model.vo.ResultObject;
import com.lc.p2p.service.loan.BidInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理哟用户投资
 */
@Controller
public class BidInfoController {

    @Autowired
    private BidInfoService bidInfoService;

    @RequestMapping(value="loan/invest")
    public @ResponseBody
    Object invest(HttpServletRequest request,
                  @RequestParam(value="loanId",required=true)Integer loanId,
                  @RequestParam(value="bidMoney",required=true)Double bidMoney,
                  Model model){


        Map<String,Object> resultMap = new HashMap<String,Object>();

        //创建map集合携带参数
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("loanId",loanId);

        //获取用户id
        User user = (User) request.getSession().getAttribute(Constants.SESSION_USER);
        paramMap.put("phone",user.getPhone());
        paramMap.put("uid",user.getId());
        paramMap.put("bidMoney",bidMoney);
        ResultObject resultObject = bidInfoService.invest(paramMap);

        if (StringUtils.equals(resultObject.getErrorCode(),Constants.SUCCESS)) {
            resultMap.put(Constants.ERROR_MESSAGE,"OK");
            return resultMap;
        }else{
            resultMap.put(Constants.ERROR_MESSAGE,"投资失败");
            return resultMap;
        }
        //TODO


    }
}
