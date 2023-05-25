package com.sily.entity.enums;

public enum EmailCodeEnum {
    EMAIL_CODE_0(0,"注册"),
    EMAIL_CODE_1(1,"找回密码");


    private Integer type;
    private String desc;

    EmailCodeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }


    public static EmailCodeEnum getType(Integer type){
        for (EmailCodeEnum item : EmailCodeEnum.values()){
            if (item.getType().equals(type)){
                return item;
            }
        }
        return null;
    }

    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
