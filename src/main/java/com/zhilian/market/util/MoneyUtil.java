package com.zhilian.market.util;

/**
 * @author ly
 * @date 2017/9/22 16:04
 * @desc 钱相关的工具
 */
public class MoneyUtil {
    private MoneyUtil(){}

    /**
     * 将字符串"分"转换成"元"（长格式），如：100分被转换为1.00元。
     * @param s
     * @return
     */
    public static String convertCent2Dollar(String s) {
        if("".equals(s) || s ==null){
            return "";
        }
        long l;
        if(s.length() != 0) {
            if(s.charAt(0) == '+') {
                s = s.substring(1);
            }
            l = Long.parseLong(s);
        } else {
            return "";
        }
        boolean negative = false;
        if(l < 0) {
            negative = true;
            l = Math.abs(l);
        }
        s = Long.toString(l);
        if(s.length() == 1)
            return(negative ? ("-0.0" + s) : ("0.0" + s));
        if(s.length() == 2)
            return(negative ? ("-0." + s) : ("0." + s));
        else
            return(negative ? ("-" + s.substring(0, s.length() - 2) + "." + s
                    .substring(s.length() - 2)) : (s.substring(0,
                    s.length() - 2)
                    + "." + s.substring(s.length() - 2)));
    }

}
