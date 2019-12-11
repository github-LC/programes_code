package com.lc.p2p.service.loan;

/**
 * 收益业务的接口
 */
public interface IncomeRecordService {
    /**
     * 生成收益记录
     * @return
     */
    void generatorIncomeRecord();

    /**
     * 收益记录返还
     */
    void generaIncomeBack();
}
