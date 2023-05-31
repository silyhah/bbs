package com.sily.entity.enums;

public enum UserIntegralOperationTypeEnum {
    REGISTER(0,"注册"),
    USER_DOWNLOAD_ATTACHMENT(1,"下载附件"),
    DOWNLOAD_ATTACHMENT(2,"附件被下载"),
    POST_COMMENT(3,"发表评论"),
    POST_ARTICLE(4,"发表文章"),
    ADMIN(5,"管理员操作"),
    DEL_ARTICLE(6,"文章被删除"),
    DEL_COMMENT(7,"评论被删除");



    private Integer type;
    private String desc;


    public UserIntegralOperationTypeEnum getUserIntegralOrderTypeEnum(Integer type){
        for (UserIntegralOperationTypeEnum item : UserIntegralOperationTypeEnum.values()){
            if (item.getType().equals(type)){
                return item;
            }
        }
        return null;
    }

    UserIntegralOperationTypeEnum(Integer code, String desc) {
        this.type = code;
        this.desc = desc;
    }


    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
