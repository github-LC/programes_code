package com.lc.p2p.web;

import com.alibaba.fastjson.JSONObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.lc.p2p.common.constants.Constants;
import com.lc.p2p.common.utils.DateUtils;
import com.lc.p2p.common.utils.HttpClientUtil;
import com.lc.p2p.model.loan.RechargeRecord;
import com.lc.p2p.model.user.User;
import com.lc.p2p.service.loan.OnlyNumberService;
import com.lc.p2p.service.loan.RechargeRecordService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户支付
 */
@Controller
public class RechargeContorller {

    @Autowired
    private OnlyNumberService onlyNumberService;
    @Autowired
    private RechargeRecordService rechargeRecordService;

    @RequestMapping(value="loan/toAlipayRecharge")
    public String alipayRecharge(HttpServletRequest request, Model model,
                                 @RequestParam(value="alipayMoney",required=true)Double alipayMoney){

        //创建支付对象
        RechargeRecord rechargeRecord = new RechargeRecord();
        //获取用户信息
        User user = (User) request.getSession().getAttribute(Constants.SESSION_USER);
        rechargeRecord.setUid((user.getId()));
        //生成全局的唯一的支付订单id(当前时间戳加redis的全局唯一数字)
        rechargeRecord.setRechargeNo(DateUtils.getDateStamp()+onlyNumberService.getOnlyNumber());

        //封装其他参数
        rechargeRecord.setRechargeDesc("支付宝支付");
        rechargeRecord.setRechargeStatus("0");
        rechargeRecord.setRechargeTime(new Date());
        rechargeRecord.setRechargeMoney(alipayMoney);

        //新增支付订单(支付状态为0：待支付)
        int result = rechargeRecordService.addRechargeRecord(rechargeRecord);

        //新增记录成功的话就向p2p-pay中传递一些请求参数
        if(result>0){

            model.addAttribute("p2p_alipay_pay_url","http://localhost:8082/pay/api/alipay");
            model.addAttribute("rechargeMoney",rechargeRecord.getRechargeMoney());
            model.addAttribute("rechargeNo",rechargeRecord.getRechargeNo());
            model.addAttribute("subject","支付宝充值");

            return "toAlipay";
        }else{
            model.addAttribute("充值人数过多，请重试...");
            return "toRechargeBack";
        }
    }


    /**
     * 验证签名并根据支付状态对余额和支付状态进行修改
     * @param signVerified
     * @param trade_no
     * @param out_trade_no
     * @param total_amount
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value="loan/alipayBack")
    public String alipayBack(@RequestParam(value="signVerified",required=true) String signVerified,
                             @RequestParam(value="trade_no",required=true) String trade_no,
                             @RequestParam(value="out_trade_no",required=true) String out_trade_no,
                             @RequestParam(value="total_amount",required=true) String total_amount,
                             Model model,
                             HttpServletRequest request){
        //验证签名
        if(StringUtils.equals(Constants.SUCCESS,signVerified)){
            //获取支付状态
            Map<String,Object> paramMap = new HashMap<String,Object>();
            //添加订单编号
            paramMap.put("out_trade_no",out_trade_no);
            paramMap.put("trade_no",trade_no);
            String jsonString = HttpClientUtil.doPost("http://localhost:8082/pay/api/alipayQuery",paramMap);


            //将json格式的字符串转换为JSONObject对象
            JSONObject jsonOBject = (JSONObject) JSONObject.parse(jsonString);
            String code = (String) jsonOBject.getJSONObject("alipay_trade_query_response").get("code");
            if(StringUtils.equals(code,"10000")) {
                //获取支付状态
                String status = (String) jsonOBject.getJSONObject("alipay_trade_query_response").get("trade_status");


                if (StringUtils.equals("TRADE_SUCCESS", status)) {

                    //交易支付成功
                    //修改账户余额和充值订单状态
                    Map<String,Object> params = new HashMap<String,Object>();
                    User user = (User) request.getSession().getAttribute(Constants.SESSION_USER);
                    params.put("uid",user.getId());
                    params.put("rechargeNo",out_trade_no);
                    params.put("total_amount",total_amount);
                    int result = rechargeRecordService.rechargeSuccess(params);

                    if(result<0){
                        model.addAttribute("trade_msg","充值失败，当前人数过多请稍后重试.....");
                        return "toRechargeBack";
                    }
                } else if (StringUtils.equals("TRADE_CLOSED", status)) {
                    //未付款交易超时关闭，或支付完成后全额退款
                    //修改订单的充值状态
                    Map<String,Object> params = new HashMap<String,Object>();
                    params.put("rechargeNo",out_trade_no);
                    params.put("rechargeStatus",2);
                    int result = rechargeRecordService.modifyRechargeRecordByRechargeNo(params);
                    if(result<0){
                        model.addAttribute("trade_msg","充值失败，当前人数过多请稍后重试.....");
                        return "toRechargeBack";
                    }
                }
            }else{
                model.addAttribute("trade_msg","通信失败，当前人数过多请稍后重试.....");
                return "toRechargeBack";
            }
        }

        return "redirect:myCenter";
    }


    @RequestMapping(value="loan/toWxpayRecharge")
    public String alipayRecharge(HttpServletRequest request,
                                 @RequestParam(value="wxpayMoney",required=true) String wxpayMoney,
                                 Model model){

        //创建支付对象
        RechargeRecord rechargeRecord = new RechargeRecord();
        //获取用户信息
        User user = (User) request.getSession().getAttribute(Constants.SESSION_USER);
        rechargeRecord.setUid((user.getId()));
        //生成全局的唯一的支付订单id(当前时间戳加redis的全局唯一数字)
        rechargeRecord.setRechargeNo(DateUtils.getDateStamp()+onlyNumberService.getOnlyNumber());

        //封装其他参数
        rechargeRecord.setRechargeDesc("微信支付");
        rechargeRecord.setRechargeStatus("0");
        rechargeRecord.setRechargeTime(new Date());
        rechargeRecord.setRechargeMoney(Double.valueOf(wxpayMoney));

        //新增支付订单(支付状态为0：待支付)
        int result = rechargeRecordService.addRechargeRecord(rechargeRecord);

        //新增记录成功的话就向p2p-pay中传递一些请求参数
        if(result>0){

            model.addAttribute("rechargeMoney",rechargeRecord.getRechargeMoney());
            model.addAttribute("rechargeNo",rechargeRecord.getRechargeNo());
            model.addAttribute("body","微信充值");


            return "showQR";
        }else{
            model.addAttribute("充值人数过多，请重试...");
            return "toRechargeBack";
        }
    }

    @RequestMapping(value="loan/showQRCode")
    public void showQRCode(HttpServletRequest request ,
                             HttpServletResponse response,
                             @RequestParam(value="body",required=true) String body,
                             @RequestParam(value="rechargeNo",required=true) String rechargeNo,
                             @RequestParam(value="rechargeMoney",required=true) String rechargeMoney) throws WriterException, IOException {

        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("body",body);
        paramMap.put("rechargeNo",rechargeNo);
        paramMap.put("rechargeMoney",rechargeMoney);
        //调用pay中的统一下单接口
        String result = HttpClientUtil.doPost("http://localhost:8082/pay/api/wxpay",paramMap);
        //获取状态码
        JSONObject jsonObject = (JSONObject) JSONObject.parse(result);
        String status = jsonObject.getString("return_code");
        if(StringUtils.equals(status,"SUCCESS")){
            //将返回的地址转换成二维码
            Map<EncodeHintType,String> hinMap = new HashMap<EncodeHintType,String>();
            hinMap.put(EncodeHintType.CHARACTER_SET,"utf-8");
            //获取二维码的地址
            String url = jsonObject.getString("code_url");
            //创建矩阵对象
            BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE,200,200,hinMap);
            OutputStream outputStream = response.getOutputStream();
            //将矩阵对象写入到页面
            MatrixToImageWriter.writeToStream(bitMatrix,"png",outputStream);
        }else{
           response.sendRedirect("toRechargeBack.jsp");
        }
    }

}
