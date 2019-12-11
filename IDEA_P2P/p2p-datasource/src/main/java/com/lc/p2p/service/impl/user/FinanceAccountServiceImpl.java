package com.lc.p2p.service.impl.user;

import com.lc.p2p.common.constants.Constants;
import com.lc.p2p.mapper.user.FinanceAccountMapper;
import com.lc.p2p.model.user.FinanceAccount;
import com.lc.p2p.service.user.FinanceAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 用户账户接口
 */
@Service
public class FinanceAccountServiceImpl implements FinanceAccountService {
    @Autowired
    private FinanceAccountMapper financeAccountMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 查询用户账户的余额
     *
     * @param uid
     * @return
     */
    @Override
    public FinanceAccount queryUserBalanceById(Integer uid) {
        FinanceAccount financeAccount = financeAccountMapper.selectUserBalanceById(uid);
        //将数据存放到redis中
        redisTemplate.opsForValue().set(Constants.FINANCEACCOUNT,financeAccount,60, TimeUnit.MINUTES);
        return financeAccount;
    }

    /**
     * 更新账户余额
     *
     * @param userId
     * @return
     */
    @Override
    public int updateBalanceByUid(Integer userId) {
        return 0;
    }

}
