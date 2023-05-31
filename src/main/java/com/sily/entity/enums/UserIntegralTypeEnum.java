package com.sily.entity.enums;

public enum UserIntegralTypeEnum {
    PLUS(1,"加"),
    MINUS(-1,"减");

    private Integer type;
    private String desc;


    public UserIntegralTypeEnum getUserIntegralTypeEnum(Integer type){
        for (UserIntegralTypeEnum item : UserIntegralTypeEnum.values()){
            if (item.getType().equals(type)){
                return item;
            }
        }
        return null;
    }

    UserIntegralTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
