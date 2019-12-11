package com.lc.p2p.timer;

import com.lc.p2p.service.loan.IncomeRecordService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * 定时刷新收益记录
 */
@Component
public class TimerManager {

    @Autowired
    private IncomeRecordService incomeRecordService ;

    private Logger logger = LogManager.getLogger(TimerManager.class);

    @Scheduled(cron="0/55 * * * * ?")
   public void generaIncomeRecord(){
        logger.info("*******************************开始生成收益记录*******************************");
        incomeRecordService.generatorIncomeRecord();
        logger.info("*******************************生成收益记录结束*******************************");
    }

    @Scheduled(cron="0/55 * * * * ?")
    public void generaIncomeBack(){
        logger.info("*******************************生成收益返还开始*******************************");
        incomeRecordService.generaIncomeBack();
        logger.info("*******************************生成收益返还结束*******************************");
    }

}
