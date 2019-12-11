package com.lc.p2p.common.constants;

import java.io.Serializable;

public class Constants implements Serializable {

    /**
     * 历史年化收益率
     */
    public static final String HISTORY_AVERAGE_RATE = "historyAverageRate";

    /**
     * 注册总人数
     */
    public static final String ALL_USER_COUNT = "allUserCount";

    /**
     * 总的投资金额
     */
    public static final String ALL_BID_MONEY = "allBidMoney";

    /**
     * 产品类型新手宝0
     */
    public static final Integer PRODUCT_TYPR_X = 0;

    /**
     * 优选产品类型1
     */
    public static final Integer PRODUCT_TYPE_U = 1;

    /**
     * 散标产品类型2
     */
    public static final Integer PRODUCT_TYPE_S = 2;

    /**
     * 手机号已经被注册返回的信息
     */
    public static final String ERROR_MESSAGE = "errorMessage";

    /**
     * 手机号未被注册返回的信息
     */
    public static final Object OK = "OK";

    /**
     * 验证码图片的宽度
     */
    public static final int WIDTH = 140;

    /**
     * 验证码图片的高度
     */
    public static final int HEIGHT = 50;

    /**
     * 验证码
     */
    public static final String CAPTCHA = "capacha";

    /**
     * 操作成功
     */
    public static final String SUCCESS = "SUCCESS";

    /**
     * 操作失败
     */
    public static final String FAIL = "FAIL";

    /**
     * 存放在session中的用户信息
     */
    public static final String SESSION_USER = "user";

    /**
     * 用户余额
     */
    public static final String USER_BALANCE = "balance";

    /**
     * 用户投资排行榜
     */
    public static final String INVEST_TOP = "invest_top";

    /**
     * redis中获取的全局唯一数字
     */
    public static final String ONLY_NUMBER ="only_number" ;

    /**
     * 账户
     */
    public static final String FINANCEACCOUNT = "financeAccount";

    /**
     * 账户
     */

}
