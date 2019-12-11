package com.lc.p2p.service.impl.loan;

import com.lc.p2p.common.constants.Constants;
import com.lc.p2p.common.utils.DateUtils;
import com.lc.p2p.mapper.loan.BidInfoMapper;
import com.lc.p2p.mapper.loan.IncomeRecordMapper;
import com.lc.p2p.mapper.loan.LoanInfoMapper;
import com.lc.p2p.mapper.user.FinanceAccountMapper;
import com.lc.p2p.model.loan.BidInfo;
import com.lc.p2p.model.loan.IncomeRecord;
import com.lc.p2p.model.loan.LoanInfo;
import com.lc.p2p.service.loan.IncomeRecordService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *处理收益相关的业务
 */
@Service("IncomeRecordService")
public class IncomeRecordServiceImpl implements IncomeRecordService {

    @Autowired
    private LoanInfoMapper loanInfoMapper;
    @Autowired
    private BidInfoMapper bidInfoMapper;
    @Autowired
    private IncomeRecordMapper incomeRecordMapper;
    @Autowired
    private FinanceAccountMapper financeAccountMapper;

    //创建日志对象
    Logger logger = LogManager.getLogger(IncomeRecordServiceImpl.class);
    /**
     * 生成收益记录
     *
     * @return
     */
    @Override
    public void generatorIncomeRecord() {

        Integer productStatus = 1;
        //查询满标产品列表
        List<LoanInfo> loanInfoList = loanInfoMapper.selectByProductStatus(productStatus);

        //遍历集合，查询每个满标招产品对应的所有的投资记录
        for(LoanInfo loanInfo : loanInfoList){

            //产品id
            Date incomeDate;
            Double incomeMoney;

            //得到满标产品对应的所有的投资记录
            Map<String, Object> paramMap = new HashMap<String,Object>();
            paramMap.put("loanId",loanInfo.getId());
            paramMap.put("begin",0);
            paramMap.put("pageSize",100);
            List<BidInfo> bidInfoList = bidInfoMapper.selectBidInfoListByLoanId(paramMap);

            //遍历集合，将每条投资记录生成收益记录
            for(BidInfo bidInfo : bidInfoList){
                 //将收益记录插入收益记录表中
                IncomeRecord incomeRecord = new IncomeRecord();
                incomeRecord.setBidId(bidInfo.getId());
                incomeRecord.setBidMoney(bidInfo.getBidMoney());
                incomeRecord.setLoanId(bidInfo.getLoanId());
                incomeRecord.setUid(bidInfo.getUid());
                //设置收益状态：0为返还，1已返还
                incomeRecord.setIncomeStatus(0);

                //判断产品类型
                if(loanInfo.getProductType() == Constants.PRODUCT_TYPR_X){
                    //新手宝的收益时间
                    incomeDate = DateUtils.getDateByAddDays(loanInfo.getProductFullTime(),loanInfo.getCycle());
                    //计算收益
                    incomeMoney = bidInfo.getBidMoney()*(loanInfo.getRate()/100/365)*loanInfo.getCycle();
                    incomeMoney = Math.round(incomeMoney*Math.pow(10,2))/Math.pow(10,2);
                }else{
                    //优选和散标的收益时间
                    incomeMoney = bidInfo.getBidMoney()*(loanInfo.getRate()/100/365)*loanInfo.getCycle()*30;
                    incomeDate = DateUtils.getDateByAddMonth(loanInfo.getProductFullTime(),loanInfo.getCycle());
                    incomeMoney = Math.round(incomeMoney*Math.pow(10,2))/Math.pow(10,2);

                }
                incomeRecord.setIncomeDate(incomeDate);
                incomeRecord.setIncomeMoney(incomeMoney);

                //新增收益记录
                int insertResult = incomeRecordMapper.insertSelective(incomeRecord);

                if(insertResult>0){
                    logger.info(bidInfo.getUid()+"用户投资的"+loanInfo.getId()+"产品的收益记录生成成功");
                }else{
                    logger.info(bidInfo.getUid()+"用户投资的"+loanInfo.getId()+"产品的收益记录生成失败");
                }
            }
            //更改产品表中产品的状态
            LoanInfo updateLoan = new LoanInfo();
            updateLoan.setId(loanInfo.getId());
            updateLoan.setProductStatus(2);
            int updateResult = loanInfoMapper.updateByPrimaryKeySelective(updateLoan);
            if(updateResult>0){
                logger.info(loanInfo.getId()+"：该产品已满标修改状态并且生成收益记录成功");
            }
        }
    }

    /**
     * 收益记录返还
     */
    @Override
    public void generaIncomeBack() {
        //查询收益时间为当前时间，收益状态为为返还的收益记录列表
        List<IncomeRecord> incomeRecordList = incomeRecordMapper.selectByIncomeRecordByDateAndStatus(0);

        //遍历集合，将投资金额和利息一起返还到账户余额中
        for(IncomeRecord incomeRecord:incomeRecordList){

            //计算投资金额和本息
            Double incomeMoney = incomeRecord.getIncomeMoney()+incomeRecord.getBidMoney();
            //将投资金额和本息加到账户余额中
            Map<String,Object> updateMap = new HashMap<String,Object>();
            updateMap.put("uid",incomeRecord.getUid());
            updateMap.put("incomeMoney",incomeMoney);
            int result = financeAccountMapper.updateBalanceByIncomeMoney(updateMap);

            //操作成功修改收益记录
            if(result>0){
                //修改收益记录为1：已返还
                IncomeRecord updateIncomeRecord = new IncomeRecord();
                updateIncomeRecord.setId(incomeRecord.getId());
                updateIncomeRecord.setIncomeStatus(1);
                int udpateResult = incomeRecordMapper.updateByPrimaryKeySelective(updateIncomeRecord);

                //修改收益成功的话打印出日志
                if(udpateResult>0){
                    logger.info(incomeRecord.getUid()+"的收益返还成功******"+incomeRecord.getId()+"的收益状态修改成功！");
                }else{
                    logger.info(incomeRecord.getUid()+"的收益返还失败******"+incomeRecord.getId()+"的收益状态修改失败！");
                }
            }else{
                logger.info(incomeRecord.getUid()+"的收益返还失败******"+incomeRecord.getId()+"的收益状态修改失败！");
            }
        }

    }
}
