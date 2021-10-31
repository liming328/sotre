package com.yaorange.jk.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  日期格式化工具类
 * @author runmu
 */
public class DateUtils {
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static String format(Date date) {
        return SIMPLE_DATE_FORMAT.format(date);
    }
    public static Date formatDate(Date date,String rqgs){
        try {
            String datestr = format(date);
            return new SimpleDateFormat(rqgs).parse(datestr);
        } catch (ParseException e) {
            return null;
        }
    }

}
