package com.lc.p2p.service.impl.loan;

import com.lc.p2p.common.constants.Constants;
import com.lc.p2p.mapper.loan.BidInfoMapper;
import com.lc.p2p.mapper.loan.LoanInfoMapper;
import com.lc.p2p.mapper.user.FinanceAccountMapper;
import com.lc.p2p.model.loan.BidInfo;
import com.lc.p2p.model.loan.LoanInfo;
import com.lc.p2p.model.vo.PaginatinoVO;
import com.lc.p2p.model.vo.ResultObject;
import com.lc.p2p.model.vo.UserBidTop;
import com.lc.p2p.service.loan.BidInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 投资业务实现
 */
@Service("BidInfoService")
public class BidInfoServiceImpl implements BidInfoService {

    @Autowired
    private BidInfoMapper bidInfoMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private FinanceAccountMapper financeAccountMapper;
    @Autowired
    private LoanInfoMapper loanInfoMapper;

    /**
     * 查询总的投资金额
     *
     * @return
     */
    public Double queryAllBidMoney() {

        //从redis中获取操作对象
        BoundValueOperations boundValueOperations = redisTemplate.boundValueOps(Constants.ALL_BID_MONEY);
        Double allBidMoney = (Double) boundValueOperations.get();

        //判断redis中是否有值
        if (null == allBidMoney) {
            //从数据库查询总的投资金额
            allBidMoney = bidInfoMapper.selectAllBidMoney();

            //将总的投资记录存放到redis中
            redisTemplate.opsForValue().set(Constants.ALL_BID_MONEY,allBidMoney,15, TimeUnit.MINUTES);
        }

        return allBidMoney;
    }

    /**
     * 根据产品的id查询产品的投资记录
     *
     * @param loanId
     * @return
     */
    @Override
    public PaginatinoVO queryBidInfoListByLoanId(Integer loanId,Integer currentPage) {

        //设置分页参数
        PaginatinoVO paginatinoVO = new PaginatinoVO();
        paginatinoVO.setCurrentPage(currentPage);
        paginatinoVO.setPageSize(5);
        paginatinoVO.setBegin((currentPage-1)*paginatinoVO.getPageSize());

        //查询投资记录和关联的用户信息
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("loanId",loanId);
        paramMap.put("begin",paginatinoVO.getBegin());
        paramMap.put("pageSize",paginatinoVO.getPageSize());

        //查询总记录数
        Integer pageTotal = bidInfoMapper.selectAllBidInfo(paramMap);
        paginatinoVO.setPageTotal(pageTotal);
        //设置总页数
        if(pageTotal%paginatinoVO.getPageSize()==0){
            paginatinoVO.setTotalPage(pageTotal/paginatinoVO.getPageSize());
        }else{
            paginatinoVO.setTotalPage(pageTotal/paginatinoVO.getPageSize()+1);
        }

        List<BidInfo> bidInfos =  bidInfoMapper.selectBidInfoListByLoanId(paramMap);
        paginatinoVO.setList(bidInfos);

        return paginatinoVO;
    }

    /**
     * 用户投资功能
     *
     * @param paramMap
     * @return
     */
    @Override
    public ResultObject invest(Map<String, Object> paramMap) {

        ResultObject resultObject = new ResultObject();
        resultObject.setErrorCode(Constants.SUCCESS);

        //获取产品版本号
        LoanInfo loanVersion = loanInfoMapper.selectByPrimaryKey((Integer) paramMap.get("loanId"));
        paramMap.put("version",loanVersion.getVersion());

        //更新剩余可投金额(使用数据库乐观锁，防止超卖现象)
        int updateLeftProductMoneyResult = loanInfoMapper.updateLeftProductMoney(paramMap);
        if(updateLeftProductMoneyResult>0){
            //更新账户余额
            int updateBalanceResult = financeAccountMapper.updateBalanceByUid(paramMap);
            if(updateBalanceResult>0){
                //新增投资记录
                BidInfo bidInfo = new BidInfo();
                bidInfo.setUid((Integer) paramMap.get("uid"));
                bidInfo.setLoanId((Integer) paramMap.get("loanId"));
                bidInfo.setBidMoney((Double) paramMap.get("bidMoney"));
                bidInfo.setBidTime(new Date());
                bidInfo.setBidStatus(1);
                int insertResult = bidInfoMapper.insertSelective(bidInfo);
                if(insertResult>0){

                    //查看是否满标
                    LoanInfo loanDetail = loanInfoMapper.selectByPrimaryKey((Integer) paramMap.get("loanId"));
                    if(loanDetail.getLeftProductMoney() == 0){
                        //产品满标，更新产品的状态
                        LoanInfo loanInfo = new LoanInfo();
                        loanInfo.setId(loanDetail.getId());
                        loanInfo.setProductStatus(1);
                        loanInfo.setProductFullTime(new Date());
                        loanInfoMapper.updateByPrimaryKeySelective(loanInfo);
                    }

                    //获取手机号码
                    String phone = (String) paramMap.get("phone");
                    //将投资金额放到redis中(用户投资排行榜)
                    redisTemplate.opsForZSet().incrementScore(Constants.INVEST_TOP,phone,(Double)paramMap.get("bidMoney"));

                }else{
                    resultObject.setErrorCode(Constants.FAIL);
                }
            }else{
                resultObject.setErrorCode(Constants.FAIL);
            }
        }else{
            resultObject.setErrorCode(Constants.FAIL);
        }
        return resultObject;
    }

    /**
     * 用户投资排行榜
     *
     * @return
     */
    @Override
    public List<UserBidTop> queryUserBidTop() {

        List<UserBidTop> userBidTopList = new ArrayList<UserBidTop>();

        //从redis中获取手机号和投资金额
        Set<UserBidTop> userBidTopSet = redisTemplate.opsForZSet().reverseRangeWithScores(Constants.INVEST_TOP,0,9);
        Iterator iterator = userBidTopSet.iterator();
        while(iterator.hasNext()){
            //获取到手机号和投资金额
            ZSetOperations.TypedTuple<Object> next = (ZSetOperations.TypedTuple<Object>) iterator.next();
            String phone = (String) next.getValue();
            Double score = next.getScore();

            //创建用户投资排行榜对象
            UserBidTop userBidTop = new UserBidTop();
            userBidTop.setPhone(phone);
            userBidTop.setScore(score);

            //将每个用户的投资记录添加到集合中
            userBidTopList.add(userBidTop);
        }

        return userBidTopList;
    }
}

