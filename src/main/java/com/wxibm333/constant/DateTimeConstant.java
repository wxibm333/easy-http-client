package com.wxibm333.constant;

/**
 * 时间格式常量类
 * <p>1. 防止子类继承；</p>
 * <p>2. 显示定义常量，屏蔽接口常量类隐藏关键字；</p>
 *
 * @author wangXin
 * @version v1.0.0
 * @date 2022-03-28 4:51 PM
 */
public final class DateTimeConstant {
    
    public DateTimeConstant() {
        throw new UnsupportedOperationException();
    }
    
    /**
     * 中国时区
     */
    public static final String CTT = "Asia/Shanghai";
    
    /**
     * 美国时区
     */
    public static final String CST = "America/Chicago";
    
    /**
     * 日本时区
     */
    public static final String JST = "Asia/Tokyo";
    
    /**
     * UTC 时间格式
     */
    public static final String UTC = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    
    /**
     * 携带时区时间
     */
    public static final String DATE_TIME_ZONE = "yyyy-MM-dd HH:mm:ss z";
    
    /**
     * 携带时区时差时间
     */
    public static final String DATE_TIME_DIFF = "yyyy-MM-dd HH:mm:ss Z";
    
    /**
     * 日期时间毫秒格式
     */
    public static final String DATE_TIME_MILL = "yyyy-MM-dd HH:mm:ss.SSS";
    
    /**
     * 日期时间格式
     */
    public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    
    /**
     * 日期格式
     */
    public static final String DATE = "yyyy-MM-dd";
    
    /**
     * 时间格式
     */
    public static final String TIME = "HH:mm:ss";
    
    /**
     * ISO basic date
     */
    public static final String BASIC_ISO_DATE = "yyyyMMdd";
}
