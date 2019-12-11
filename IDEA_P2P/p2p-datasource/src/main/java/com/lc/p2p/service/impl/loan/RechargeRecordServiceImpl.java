package com.lc.p2p.service.impl.loan;

import com.lc.p2p.mapper.loan.RechargeRecordMapper;
import com.lc.p2p.mapper.user.FinanceAccountMapper;
import com.lc.p2p.model.loan.RechargeRecord;
import com.lc.p2p.service.loan.RechargeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

/**
 * 用户支付接口
 */
@Service("RechargeRecordService")
public class RechargeRecordServiceImpl implements RechargeRecordService {

    @Autowired
    private RechargeRecordMapper rechargeRecordMapper;
    @Autowired
    private FinanceAccountMapper financeAccountMapper;

    /**
     * 新增待支付订单
     *
     * @return
     */
    @Override
    public int addRechargeRecord(RechargeRecord rechargeRecord) {
        return rechargeRecordMapper.insertSelective(rechargeRecord);
    }

    /**
     * 支付宝充值成功
     *
     * @param params
     * @return
     */
    @Override
    public int rechargeSuccess(Map<String, Object> params) {

        //修改账户余额
        int result = financeAccountMapper.updateBalanceByRechargeSuccess(params);
        if(result>0){
            //修改订单支付状态
            params.put("rechargeStatus",1);
            result = rechargeRecordMapper.updateRechargeByRechargeNo(params);
        }



        return result;
    }

    /**
     * 充值失败修改订订单充值状态
     *
     * @param params
     * @return
     */
    @Override
    public int modifyRechargeRecordByRechargeNo(Map<String, Object> params) {
        return rechargeRecordMapper.updateRechargeByRechargeNo(params);
    }
}
