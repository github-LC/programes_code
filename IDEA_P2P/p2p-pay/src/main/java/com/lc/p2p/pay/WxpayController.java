package com.lc.p2p.pay;

import com.github.wxpay.sdk.WXPayUtil;
import com.lc.p2p.common.utils.HttpClientUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信充值
 */
@Controller
public class WxpayController {

    @RequestMapping(value="api/wxpay")
    public @ResponseBody Object wxpay(HttpServletRequest reqeust,
                                      @RequestParam(value="body",required=true) String body,
                                      @RequestParam(value="out_trade_no",required=true) String out_trade_no,
                                      @RequestParam(value="rechargeMoney",required=true) Double rechargeMoney) throws Exception {
        //封装统一接口的参数
        Map<String,String> paramMap = new HashMap<String,String>();
        paramMap.put("appid","wx8a3fcf509313fd74");
        paramMap.put("mch_id","1361137902");
        paramMap.put("nonce_str", WXPayUtil.generateNonceStr());
        paramMap.put("body",body);

        //将数值转换为bigDecimal去除小数点
        BigDecimal bigDecimal = new BigDecimal(rechargeMoney);
        BigDecimal multiply =  bigDecimal.multiply(new BigDecimal(100));
        paramMap.put("total_fee",multiply.toString());
        paramMap.put("spbill_create_ip","localhost");
        paramMap.put("product_id",out_trade_no);
        paramMap.put("out_trade_no",out_trade_no);
        paramMap.put("trade_type","NATIVE");

        //生成签名值
        String signature = WXPayUtil.generateSignature(paramMap,"367151c5fd0d50f1e34a68a802d6bbca");
        paramMap.put("sign",signature);

        //将map集合转换成xml格式
        String requestDateXml = WXPayUtil.mapToXml(paramMap);
        //调用统一接口
        String responseDateXml =  HttpClientUtil.doPost("https://api.mch.weixin.qq.com/pay/unifiedorder",requestDateXml);

        //将结果转换成集合
        Map<String,String> resultMap = WXPayUtil.xmlToMap(responseDateXml);

        return "resultMap";
    }
}
