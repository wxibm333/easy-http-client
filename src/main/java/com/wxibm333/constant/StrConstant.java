package com.wxibm333.constant;

/**
 * 字符串常量类
 * <p>1. 防止子类继承；</p>
 * <p>2. 显示定义常量，屏蔽接口常量类隐藏关键字；</p>
 *
 * @author wangXin
 * @version v1.0.0
 * @date 2022-03-28 4:51 PM
 */
public final class StrConstant {
    
    public StrConstant() {
        throw new UnsupportedOperationException();
    }
    
    /**
     * 字符串常量：星号 {@code "*"}
     */
    public static final String ASTERISK = "*";
    
    /**
     * 字符串常量：点星号 {@code ".*"}
     */
    public static final String DOT_ASTERISK = ".*";
    
    /**
     * 字符串常量：反斜杠星 {@code "\\*"} <br> 用途：用于正则表达式，如：{@code "Pattern.matches(patternStr.replaceAll("\\*", ".*"), str)"}
     */
    public static final String BACKSLASH_ASTERISK = "\\*";
    
    /**
     * 字符串常量：波浪号 {@code "~"}
     */
    public static final String TILDE = "~";
    
    /**
     * 字符串常量：制表符 {@code "\t"}
     */
    public static final String TAB = "	";
    
    /**
     * 字符串常量：空格 {@code " "}
     */
    public static final String SPACE = " ";
    
    /**
     * 字符串常量：点 {@code "."}
     */
    public static final String DOT = ".";
    
    /**
     * 字符串常量：双点 {@code ".."} <br> 用途：作为指向上级文件夹的路径，如：{@code "../path"}
     */
    public static final String DOUBLE_DOT = "..";
    
    /**
     * 字符串常量：省略号 {@code "..."}
     */
    public static final String ELLIPSIS = "...";
    
    /**
     * 字符串常量：斜杠 {@code "/"}
     */
    public static final String SLASH = "/";
    
    /**
     * 字符串常量：竖线 {@code "|"}
     */
    public static final String BAR = "|";
    
    /**
     * 字符串常量：反斜杠 {@code "\\"}
     */
    public static final String BACKSLASH = "\\";
    
    /**
     * 字符串常量：回车符 {@code "\r"} <br> 解释：该字符常用于表示 Linux 系统和 MacOS 系统下的文本换行
     */
    public static final String CR = "\r";
    
    /**
     * 字符串常量：换行符 {@code "\n"}
     */
    public static final String LF = "\n";
    
    /**
     * 字符串常量：Windows 换行 {@code "\r\n"} <br> 解释：该字符串常用于表示 Windows 系统下的文本换行
     */
    public static final String CRLF = "\r\n";
    
    /**
     * 字符串常量：下划线 {@code "_"}
     */
    public static final String UNDERLINE = "_";
    
    /**
     * 字符串常量：减号（连接符） {@code "-"}
     */
    public static final String DASHED = "-";
    
    /**
     * 字符串常量：加号/正 {@code "+"}
     */
    public static final String POSITIVE = "+";
    
    /**
     * 字符串常量：等于号/等号 {@code "="}
     */
    public static final String EQUAL = "=";
    
    /**
     * 字符串常量：逗号 {@code ","}
     */
    public static final String COMMA = ",";
    
    /**
     * 字符串常量：左括号/开括号 {@code "("}
     */
    public static final String LEFT_PARENTHESIS = "(";
    
    /**
     * 字符串常量：右括号/闭括号 {@code ")"}
     */
    public static final String RIGHT_PARENTHESIS = ")";
    
    /**
     * 字符串常量：左大括号/左花括号 <code>"{"</code>
     */
    public static final String LEFT_BRACE = "{";
    
    /**
     * 字符串常量：右大括号/右花括号 <code>"}"</code>
     */
    public static final String RIGHT_BRACE = "}";
    
    /**
     * 字符串常量：中括号（左） {@code "["}
     */
    public static final String LEFT_BRACKET = "[";
    
    /**
     * 字符串常量：中括号（右） {@code "]"}
     */
    public static final String RIGHT_BRACKET = "]";
    
    /**
     * 字符串常量：冒号 {@code ":"}
     */
    public static final String COLON = ":";
    
    /**
     * 字符串常量：分号 {@code ";"}
     */
    public static final String SEMICOLON = ";";
    
    /**
     * 字符串常量：美元符号 {@code "$"}
     */
    public static final String DOLLAR_SIGN = "$";
    
    /**
     * 字符串常量：艾特 {@code "@"}
     */
    public static final String AT = "@";
    
    /**
     * 字符串常量：井号，英语国家是hash，美语是pound,音乐里作sharp,如C# {@code "#"}
     */
    public static final String POUND = "#";
    
    /**
     * 字符串常量：问号 {@code "?"}
     */
    public static final String QUESTION_MARK = "?";
    
    /**
     * 字符串常量：小于号 {@code "<"}
     */
    public static final String LESS = "<";
    
    /**
     * 字符串常量：小于号 {@code "<"}
     */
    public static final String LESS_EQUAL = "<=";
    
    /**
     * 字符串常量：大于号 {@code ">"}
     */
    public static final String GREATER = ">";
    
    /**
     * 字符串常量：小于号 {@code "<"}
     */
    public static final String GREATER_EQUAL = ">=";
    
    /**
     * 字符串常量：百分号 {@code "%"}
     */
    public static final String PERCENT = "%";
    
    /**
     * 字符串常量：脱字符号/插入符号 {@code "^"}
     */
    public static final String CARET = "^";
    
    /**
     * 字符串常量：和/引用 {@code "&"}
     */
    public static final String AMPERSAND = "&";
    
    /**
     * 字符串常量：空 JSON {@code "{}"}
     */
    public static final String EMPTY_JSON = "{}";
}
