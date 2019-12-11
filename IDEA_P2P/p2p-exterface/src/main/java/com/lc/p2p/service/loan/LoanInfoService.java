package com.lc.p2p.service.loan;

import com.lc.p2p.model.loan.LoanInfo;
import com.lc.p2p.model.vo.PaginatinoVO;
import com.lc.p2p.model.vo.ResultObject;

import java.util.List;
import java.util.Map;

/**
 * 查询产品的信息
 */
public interface LoanInfoService {

    /**
     * 获取平台历史年化收益率
     * @return
     */
    Double queryHistoryAverageRate();

    /**
     * 根据产品类型查询产品信息列表
     * @param map
     * @return
     */
    List<LoanInfo> queryLoanInfoByProductType(Map<String, Object> map);

    /**
     * 分页查询产品列表
     * @param
     * @return
     */
    PaginatinoVO queryLoanInfoByPage(Integer currentPage,Integer ptype);

    /**
     * 查询产品详细信息
     * @param loanId
     * @return
     */
    LoanInfo queryLoanInfoById(Integer loanId);

}
