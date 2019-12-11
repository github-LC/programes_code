package com.lc.p2p.pay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.lc.p2p.common.constants.Constants;
import com.lc.p2p.config.AlipayConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 向支付宝发起支付请求
 */
@Controller
public class

AlipayController {

    @Autowired
    private AlipayConfig alipayConfig;

    /**
     * 向支付宝发起支付请求
     * @param request
     * @param model
     * @param out_trade_no
     * @param total_amount
     * @param subject
     * @param body
     * @return
     * @throws UnsupportedEncodingException
     * @throws AlipayApiException
     */
    @RequestMapping(value="api/alipay")
    public String alipay(HttpServletRequest request, Model model,
                         @RequestParam(value="out_trade_no",required=true) String out_trade_no,
                         @RequestParam(value="total_amount",required=true) Double total_amount,
                         @RequestParam(value="subject",required=true) String subject,
                         @RequestParam(value="body",required=true) String body) throws UnsupportedEncodingException, AlipayApiException {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.getGatewayUrl(),
                alipayConfig.getApp_id(),
                alipayConfig.getMerchant_private_key(),
                "json",
                alipayConfig.getCharset(),
                alipayConfig.getAlipay_public_key(),
                alipayConfig.getSign_type());

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(alipayConfig.getReturn_url());
        alipayRequest.setNotifyUrl(alipayConfig.getNotify_url());

      /*  //商户订单号，商户网站订单系统中唯一订单号，必填
        out_trade_no = new String(request.getParameter("WIDout_trade_no"));
        //付款金额，必填
        total_amount = new Double(request.getParameter("WIDtotal_amount"));
        //订单名称，必填
        subject = new String(request.getParameter("WIDsubject"));
        //商品描述，可空
        body = new String(request.getParameter("WIDbody"));*/

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
        //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
        //		+ "\"total_amount\":\""+ total_amount +"\","
        //		+ "\"subject\":\""+ subject +"\","
        //		+ "\"body\":\""+ body +"\","
        //		+ "\"timeout_express\":\"10m\","
        //		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();

        //输出
        System.out.println(result);
        model.addAttribute("result",result);

        return "toAlipay";
    }

    /**
     * 获取支付宝同步返回的参数
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value="api/alipayBack")
    public String alipayBack(HttpServletRequest request,Model model) throws UnsupportedEncodingException, AlipayApiException {

        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr);
            params.put(name, valueStr);
        }

             //调用SDK验证签名
           Boolean signVerified = AlipaySignature.rsaCheckV1(params,
                    alipayConfig.getAlipay_public_key(),
                    alipayConfig.getCharset(),
                    alipayConfig.getSign_type());


        if(signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no"));

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no"));

            //付款金额
            String total_amount = new String(request.getParameter("total_amount"));

            //验签成功,将返回的参数传递到alipayBack页面
            //将参数添加到alipayBack页面返回提交到p2p-web 中
            //设置提交路径
            model.addAttribute("signVerified", Constants.SUCCESS);

        }else {
            model.addAttribute("signVerified",Constants.FAIL);
        }

        model.addAttribute("p2p_pay_alipay_return_url","http://localhost:8080/p2p/loan/alipayBack");
        model.addAttribute("params",params);
        return "alipayBack";

    }

    /**
     * 查询支付状态
     * @param request
     * @param out_trade_no
     * @param trade_no
     * @return
     * @throws AlipayApiException
     */
    @RequestMapping(value="api/alipayQuery")
    public @ResponseBody Object alipayQuery(HttpServletRequest request,
                                            @RequestParam(value="out_trade_no",required=true) String out_trade_no,
                                            @RequestParam(value="trade_no",required=true) String trade_no) throws AlipayApiException {

        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.getGatewayUrl(),alipayConfig.getApp_id(),
                alipayConfig.getMerchant_private_key(),
                "json",
                alipayConfig.getCharset(),
                alipayConfig.getAlipay_public_key(),
                alipayConfig.getSign_type());

        //设置请求参数
        AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();

       /* //商户订单号，商户网站订单系统中唯一订单号
        String out_trade_no = new String(request.getParameter("WIDTQout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
        //支付宝交易号
        String trade_no = new String(request.getParameter("WIDTQtrade_no").getBytes("ISO-8859-1"),"UTF-8");*/
        //请二选一设置

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"}");

        //请求
        String result = alipayClient.execute(alipayRequest).getBody();

        //输出
        return result;

        //TODO
    }
}
