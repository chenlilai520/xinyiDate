package com.example.esdemotest.conext;

import java.util.regex.Pattern;

/**
 * @author Administrator
 * @date 2017/5/24
 */
public class RegexpConstant {

    /**
     * 手机号码
     */
    public static final String MOBILE_NUM = "^1\\d{10}$";

    /**
     * 数字4-6位
     */
    public static final String NUM_4_6 = "^\\d{4,6}$";

    /**
     * 数字或者字母，长度至少为1
     */
    public static final String NUM_CHAR_ATLEAST_ONCE = "^[A-Za-z0-9]+$";

    /**
     * 汉字2-20个
     */
    public static final String CHINESE_CHAR_2_TO_20 = "^[\\u4e00-\\u9fa5]{2,20}$";
    /**
     * 数字
     */
    public static final String NUM_ATLEAST_ONCE = "^[0-9]+$";

    public static final String PASSWORD_RULE = "^(?![0-9]+$)(?![a-zA-Z]+$)[a-zA-Z0-9]{6,20}";

    public static final Pattern CHARACTER_REPLACEMENT = Pattern.compile("\\{(\\d)\\}");

    public final static Pattern VERSION_PREFIX_PATTERN = Pattern.compile("v(\\d+)/");

    public final static Pattern AUTHORIZE_LOGIN = Pattern.compile("authorize_login:(\\d+)");

    public final static Pattern TEMPORARY_LOGIN = Pattern.compile("temporary_login:(\\d+)");

    public final static Pattern URL = Pattern.compile("/cifmedia/api(/account/+|/homepage/unreceivedBatch|/contact/getNearbyTimeStore|/banner)");

    /**
     * 日期校验
     */
    public final static String DATE = "^\\d{4}-\\d{2}-\\d{2}$";
}
