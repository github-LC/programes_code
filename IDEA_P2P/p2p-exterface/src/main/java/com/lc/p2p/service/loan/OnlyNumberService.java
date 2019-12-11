package com.lc.p2p.service.loan;

/**
 * 生成全局唯一数字
 */
public interface OnlyNumberService {

    /**
     * 从redis中获取全局唯一数字
     * @return
     */
    Long getOnlyNumber();
}
