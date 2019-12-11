package com.lc.p2p.service.impl.loan;

import com.lc.p2p.common.constants.Constants;
import com.lc.p2p.service.loan.OnlyNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service("OnlyNumberService")
public class OnlyNumberServiceImpl implements OnlyNumberService {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 从redis中获取全局唯一数字
     *
     * @return
     */
    @Override
    public Long getOnlyNumber() {
        return redisTemplate.opsForValue().increment(Constants.ONLY_NUMBER,1);
    }
}
