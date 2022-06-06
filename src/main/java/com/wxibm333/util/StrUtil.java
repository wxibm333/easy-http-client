package com.wxibm333.util;

/**
 * @author wangXin
 * @version v1.0.0
 * @date 2022-06-06 3:44 PM
 */
public final class StrUtil {
    
    public static boolean isBlankChar(int c) {
        return Character.isWhitespace(c) || Character.isSpaceChar(c) || c == '\ufeff' || c == '\u202a' || c == '\u0000';
    }
    
    public static boolean isBlank(CharSequence str) {
        int length;
        if ((str == null) || ((length = str.length()) == 0)) {
            return true;
        }
        for (int i = 0; i < length; i++) {
            // 只要有一个非空字符即为非空字符串
            if (!StrUtil.isBlankChar(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
