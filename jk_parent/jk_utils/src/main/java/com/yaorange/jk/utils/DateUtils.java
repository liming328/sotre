package com.yaorange.jk.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  日期格式化工具类
 * @author runmu
 */
public class DateUtils {
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static String fmt(Date date)
    {
        return SIMPLE_DATE_FORMAT.format(date);
    }

}
