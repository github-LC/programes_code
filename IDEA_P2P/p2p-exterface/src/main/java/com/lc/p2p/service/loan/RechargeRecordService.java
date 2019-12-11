package com.lc.p2p.service.loan;

import com.lc.p2p.model.loan.RechargeRecord;

import java.util.Map;

/**
 * 处理用户支付接口
 */
public interface RechargeRecordService {


    /**
     * 新增待支付订单
     * @return
     */
    int addRechargeRecord(RechargeRecord rechargeRecord);

    /**
     * 支付宝充值成功
     * @param params
     * @return
     */
    int rechargeSuccess(Map<String, Object> params);

    /**
     * 充值失败修改订订单充值状态
     * @param params
     * @return
     */
    int modifyRechargeRecordByRechargeNo(Map<String, Object> params);
}
