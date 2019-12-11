package com.lc.p2p.service.user;

import com.lc.p2p.model.user.FinanceAccount;
import com.lc.p2p.model.user.User;
import com.lc.p2p.model.vo.ResultObject;

/**
 * 用户相关业务
 */
public interface UserService {


    /**
     * 平台注册总人数
     *
     * @return
     */
    Long queryAllUserCount();

    /**
     * 根据手机号查询用户信息
     *
     * @param phone
     * @return
     */
    User queryUserByPhone(String phone);

    /**
     * 完成用户注册后新增用户
     *
     * @param phone
     * @param loginPassword
     * @return
     */
    ResultObject regist(String phone, String loginPassword);

    /**
     * 根据用户id更新用户信息
     *
     * @param updateUser
     * @return
     */
    int modifyUserInfoById(User updateUser);

    /**
     * 用户登陆验证
     *
     * @param phone
     * @param loginPassword
     * @return
     */
    User userLogin(String phone, String loginPassword);
}

