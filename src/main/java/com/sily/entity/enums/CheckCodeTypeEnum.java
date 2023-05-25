package com.sily.entity.enums;

public enum CheckCodeTypeEnum {


    CHECK_CODE_0(0,"登录/注册验证码"),
    CHECK_CODE_1(1,"发送邮箱验证码");


    private Integer type;
    private String desc;

    public static CheckCodeTypeEnum getType(Integer type){
        for (CheckCodeTypeEnum item : CheckCodeTypeEnum.values()){
            if (item.getType().equals(type)){
                return item;
            }
        }
        return null;
    }

    CheckCodeTypeEnum(Integer type, String desc) {

        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
