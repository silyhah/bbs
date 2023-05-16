package com.sily.entity.enums;

public enum VerifyRegexEnum {

    NO("","不校验"),
    IP("^((\\\\d{1,2}|1\\\\d{2}|2[0-4]\\\\d|25[0-5])\\\\.){3}(\\\\d{1,2}|1\\\\d{2}|2[0-4]\\\\d|25[0-5])$","ip地址"),
    POSITIVE_INTEGER("^[1-9]\\d*$ 或 ^([1-9][0-9]*){1,3}$ 或 ^\\+?[1-9][0-9]*$","正整数"),
    NUMBER_LETTER_UNDER_LINE("^[\\u4E00-\\u9FA5A-Za-z0-9_]+$","数字，字母，中文，下划线"),
    EMAIL("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$","邮箱"),
    PHONE("/^\\d{3}-\\d{8}$|^\\d{4}-\\d{7}$/","电话"),
    PASSWORD("^\\w+$ 或 ^\\w{3,20}$","数字，字母，下划线");


    private String regex;

    private String desc;

    VerifyRegexEnum(String regex, String desc) {
        this.regex = regex;
        this.desc = desc;
    }

    public String getRegex() {
        return regex;
    }

    public String getDesc() {
        return desc;
    }
}
