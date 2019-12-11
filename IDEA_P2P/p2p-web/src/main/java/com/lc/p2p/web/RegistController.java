package com.lc.p2p.web;

import com.alibaba.fastjson.JSONObject;
import com.lc.p2p.common.constants.Constants;
import com.lc.p2p.common.utils.HttpClientUtil;
import com.lc.p2p.model.user.User;
import com.lc.p2p.model.vo.ResultObject;
import com.lc.p2p.service.user.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 处理注册界面相关业务
 */
@Controller
public class RegistController {

    @Autowired
    private UserService userService;

    /**
     * 跳转到用户注册界面
     * @return
     */
    @RequestMapping(value="/loan/regist")
    public String userRegist() {

        return "register";
    }


    /**
     * 验证手机号是否被注册
     * @param request
     * @param phone
     * @return
     */
    @RequestMapping(value="/loan/checkPhone")
    public @ResponseBody Object checkPhone(HttpServletRequest request,
                      HttpServletResponse response,
                      @RequestParam(value="phone",required=true) String phone){

        //定义一个集合存放查询信息
        Map<String,Object> resultMap = new HashMap<String,Object>();

        //去数据库查询手机号是否被注册
        User user = userService.queryUserByPhone(phone);

        //判断用户是否存在
        if(null != user){
            resultMap.put(Constants.ERROR_MESSAGE,"该手机号已经被注册,请更换手机号");
            return resultMap;
        }

        resultMap.put(Constants.ERROR_MESSAGE,Constants.OK);

        return resultMap;
    }

    /**
     * 用户注册
     * @param request
     * @param phone
     * @param loginPassword
     * @param replayLoginPassword
     * @param captcha
     * @return
     */
    @RequestMapping(value="/loan/checkRegist")
    public @ResponseBody Object regist(HttpServletRequest request,
                             @RequestParam(value="phone",required=true)String phone,
                             @RequestParam(value="loginPassword",required=true) String loginPassword,
                             @RequestParam(value="replayLoginPassword",required=true) String replayLoginPassword,
                             @RequestParam(value="captcha",required=true) String captcha){

        //封装注册结果
        Map<String,Object> resultMap = new HashMap<String,Object>();

        //对手机号进行校验
        if(!Pattern.matches("^1[1-9]\\d{9}$",phone)){
            resultMap.put(Constants.ERROR_MESSAGE,"请输入正确的手机号");
            return resultMap;
        }

         //对密码进行校验
        if(!StringUtils.equals(loginPassword,replayLoginPassword)){
            resultMap.put(Constants.ERROR_MESSAGE,"密码输入不正确");
            return resultMap;
        }

        //校验验证码
        //获取session中的随机验证码
        String relCaptcha = (String) request.getSession().getAttribute(Constants.CAPTCHA);

        if(!StringUtils.equalsIgnoreCase(captcha,relCaptcha)){
            resultMap.put(Constants.ERROR_MESSAGE,"验证码输入错误");
            return resultMap;
        }

        //新增用户
        ResultObject resultObject = userService.regist(phone,loginPassword);
        if(!StringUtils.equals(resultObject.getErrorCode(),Constants.SUCCESS)){
            resultMap.put(Constants.ERROR_MESSAGE,"注册失败");
            return resultMap;
        }

        //将用户信息存放到session中
        request.getSession().setAttribute(Constants.SESSION_USER,userService.queryUserByPhone(phone));
        resultMap.put(Constants.ERROR_MESSAGE,Constants.OK);

        return resultMap;
    }

    /**
     * 跳转到实名认证页面
     * @return
     */
    @RequestMapping(value="/loan/realName")
    public String toRealName(){
        return "realName";
    }

    @RequestMapping(value="/loan/checkVerifyRealName")
    public @ResponseBody Object verifyRealName(HttpServletRequest request,
                                               @RequestParam(value="realName",required=true) String realName,
                                               @RequestParam(value="idCard",required=true) String idCard,
                                               @RequestParam(value="replayIdCard",required=true) String replayIdCard,
                                               @RequestParam(value="captcha",required = true) String captcha){

        Map<String,Object> resultMap = new HashMap<String,Object>();

        //校验用户姓名
        if("" == realName){
            resultMap.put(Constants.ERROR_MESSAGE,"请输入真实姓名");
            return realName;
        }else if(!Pattern.matches("^[\\u4e00-\\u9fa5]{0,}$",realName)){
            resultMap.put(Constants.ERROR_MESSAGE,"请输入正确的姓名");
            return realName;
        }

        //验证身份证号
        if("" == idCard){
            resultMap.put(Constants.ERROR_MESSAGE,"请输入身份证号");
            return realName;
        }else if(!Pattern.matches("(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)",idCard)){
            resultMap.put(Constants.ERROR_MESSAGE,"请输入正确的身份证号");
            return realName;
        }else if(!StringUtils.equals(idCard,replayIdCard)){
            resultMap.put(Constants.ERROR_MESSAGE,"两次输入的身份证号不一致，请重新输入");
            return realName;
        }

        //前台信息校验成功后调用实名认证接口验证身份证号与真实姓名是否匹配
        //封装接口所需的参数
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("realName",realName);
        paramMap.put("cardNo",idCard);
        paramMap.put("appkey","dcd11ef4af3c873a32620ebab5ad9e81");

        //通过httpclient发起请求
        String jsonString = HttpClientUtil.doPost("https://way.jd.com/youhuoBeijing/test",paramMap);
       /* String jsonString = "{ \"code\":\"10000\",\"charge\":false,\"remain\":1305,\"msg\":\"查询成功\",\"result\":{"+
                "\"error_code\":0,\"reason\":\"成功\",\"result\":{\"realname\":\"乐天磊\",\"idcard\":\"350721197702134399\",\"isok\":true}}";*/
        //将返回的json格式的字符串进行解析
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        String code = jsonObject.getString("code");
        if(!StringUtils.equals(code,"10000")){
            resultMap.put(Constants.ERROR_MESSAGE,"通信失败，请稍后重试");
            return  resultMap;
        }
        Boolean result = jsonObject.getJSONObject("result").getJSONObject("result").getBoolean("isok");
        if(!result){
            resultMap.put(Constants.ERROR_MESSAGE,"真实姓名与身份证号码不匹配");
            return  resultMap;
        }


        //根据用户id更新用户信息
        //获取用户对象
        User user = (User) request.getSession().getAttribute(Constants.SESSION_USER);
        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setName(realName);
        updateUser.setIdCard(idCard);
        int updateResult = userService.modifyUserInfoById(updateUser);

        //如果更新成功更新session中用户的信息
        if(updateResult>0){
            request.getSession().setAttribute(Constants.SESSION_USER,userService.queryUserByPhone(user.getPhone()));
            //认证成功
            resultMap.put(Constants.ERROR_MESSAGE,Constants.OK);
        }

        return resultMap;

    }


}
