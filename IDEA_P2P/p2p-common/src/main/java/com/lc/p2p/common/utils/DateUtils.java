package com.lc.p2p.common.utils;

import sun.text.resources.cldr.FormatData;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理工具类：返回指定一段时间后的日期
 */
public class DateUtils {


    /**
     * 返回指定天数后的日期
     * @param date
     * @param days
     * @return
     */
    public static Date getDateByAddDays(Date date, int days){

        //创建日期处理类
        Calendar calendar = Calendar.getInstance();

        //指定的日期
        calendar.setTime(date);

        //增加天数
        calendar.add(Calendar.DATE,days);

        return calendar.getTime();
    }


    /**
     * 返回指定月数后的日期
     * @param date
     * @param months
     * @return
     */
    public static Date getDateByAddMonth(Date date, int months){

        //创建日期处理类
        Calendar calendar = Calendar.getInstance();

        //指定的日期
        calendar.setTime(date);

        //增加天数
        calendar.add(Calendar.MONTH,months);

        return calendar.getTime();
    }


    public static String getDateStamp(){
        return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
    };
}
