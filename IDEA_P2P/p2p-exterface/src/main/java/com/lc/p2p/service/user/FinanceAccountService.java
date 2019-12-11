package com.lc.p2p.service.user;

import com.lc.p2p.model.user.FinanceAccount;

/**
 * 处理用户账户的接口
 */
public interface FinanceAccountService {

    /**
     * 查询账户的余额
     * @param id
     * @return
     */
    FinanceAccount queryUserBalanceById(Integer id);

    /**
     * 更新账户余额
     * @param userId
     * @return
     */
    int updateBalanceByUid(Integer userId);
}
