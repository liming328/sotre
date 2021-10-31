package com.yaorange.jk.utils;

/**
 * 〈字符工具类〉
 *
 * @author coach tam
 * @create 2018/6/25
 * @since 1.0.0
 * 〈坚持灵活 灵活坚持〉
 */
public class StringUtils {
    /**
     *将  a,b  格式---> 'a','b'
     * @param str 需要转换的字符
     * @param joinChar  需要添加的环绕字符
     * @return
     */
    public static String joinAroundStr(String str, String joinChar) {
        String[] ids = str.split(",");
        String resultStr = "";
        for(String id:ids)
        {
            resultStr+=joinChar+id+joinChar+",";
        }
        //去掉最后一个','字符    'a','b', -->'a','b'
        resultStr = resultStr.substring(0,resultStr.length() - 1);
        return resultStr;
    }

    public static String toString(String[] a) {
        if (a == null)
            return "null";

        int iMax = a.length - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        for (int i = 0; ; i++) {
            b.append(String.valueOf(a[i]));
            if (i == iMax)
                return b.toString();
            b.append(",");
        }
    }
}
