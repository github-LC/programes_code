package com.lc.p2p.service.impl.user;

import com.lc.p2p.common.constants.Constants;
import com.lc.p2p.mapper.user.FinanceAccountMapper;
import com.lc.p2p.mapper.user.UserMapper;
import com.lc.p2p.model.user.FinanceAccount;
import com.lc.p2p.model.user.User;
import com.lc.p2p.model.vo.ResultObject;
import com.lc.p2p.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 用户业务的实现接口
 */
@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private FinanceAccountMapper financeAccountMapper;

    /**
     * 平台注册总人数
     *
     * @return
     */
    @Override
    public Long queryAllUserCount() {
        //从redis中获取指定key值得操作对象
        BoundValueOperations boundValueOperations = redisTemplate.boundValueOps(Constants.ALL_USER_COUNT);
        //获取value值
        Long allUserCount = (Long) boundValueOperations.get();

        //判断redis缓存中是否有值
        if (null == allUserCount) {
            //从数据库中查询注册总人数
            allUserCount = userMapper.selectAllUserCount();

            //将总人数存放到redis中
            redisTemplate.opsForValue().set(Constants.ALL_USER_COUNT,allUserCount,15, TimeUnit.MINUTES);
            System.out.println(allUserCount+"===========================================================");
        }
        return allUserCount;
    }

    /**
     * 根据手机号查询用户信息
     *
     * @param phone
     * @return
     */
    @Override
    public User queryUserByPhone(String phone) {
        return userMapper.selectUserByPhone(phone);
    }

    /**
     * 完成用户注册后新增用户
     *
     * @param phone
     * @param loginPassword
     * @return
     */
    @Override
    public ResultObject regist(String phone, String loginPassword) {

        ResultObject resultObject = new ResultObject();
        User user = new User();
        user.setPhone(phone);
        user.setAddTime(new Date());
        user.setLastLoginTime(new Date());
        user.setLoginPassword(loginPassword);
        int userResult = userMapper.insertSelective(user);

        //如果注册成功就新开账户
        if(userResult > 0){
            resultObject.setErrorCode(Constants.SUCCESS);
            //开设账户
            User selectUser = userMapper.selectUserByPhone(phone);
            FinanceAccount financeAccount = new FinanceAccount();
            financeAccount.setUid(selectUser.getId());
            financeAccount.setAvailableMoney(888.88);
            int  financeAccountResult = financeAccountMapper.insertSelective(financeAccount);

            if(financeAccountResult < 0){
                resultObject.setErrorCode(Constants.FAIL);
            }
        }else{
            resultObject.setErrorCode(Constants.FAIL);
        }

        return resultObject;
    }

    /**
     * 根据用户id更新用户信息
     *
     * @param updateUser
     * @return
     */
    @Override
    public int modifyUserInfoById(User updateUser) {
        return userMapper.updateByPrimaryKeySelective(updateUser);
    }

    /**
     * 用户登陆
     *
     * @param phone
     * @param loginPassword
     * @return
     */
    @Override
    public User userLogin(String phone, String loginPassword) {

        //去数据库查询该用户是否存在
        User user = userMapper.selectUserByPhoneAndLoginPassword(phone,loginPassword);
        if(null != user){
            //这里需要新建一个用户对象去携带数据，如果使用查询出来的user对象就会更新登陆时间，而且页面的一些地方需要上次登陆时间
            User newUser = new User();
            newUser.setId(user.getId());
            newUser.setLastLoginTime(new Date());

            //更新用户
            int result = userMapper.updateByPrimaryKeySelective(newUser);
            if (result>0) {
                return user;
            }
        }

        return user;
    }
}
