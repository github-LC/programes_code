package com.lc.p2p.service.loan;

import com.lc.p2p.model.loan.BidInfo;
import com.lc.p2p.model.vo.PaginatinoVO;
import com.lc.p2p.model.vo.ResultObject;
import com.lc.p2p.model.vo.UserBidTop;

import java.util.List;
import java.util.Map;

/**
 * 投资业务接口
 */
public interface BidInfoService {

    /**
     * 查询总的投资金额
     * @return
     */
    Double queryAllBidMoney();

    /**
     * 根据产品的id查询产品的投资记录
     * @param loanId
     * @return
     */
    PaginatinoVO queryBidInfoListByLoanId(Integer loanId,Integer currentPage);

    /**
     * 处理用户投资
     * @param paramMap
     * @return
     */
    ResultObject invest(Map<String, Object> paramMap);

    /**
     * 用户投资排行榜
     * @return
     */
    List<UserBidTop> queryUserBidTop();
}
