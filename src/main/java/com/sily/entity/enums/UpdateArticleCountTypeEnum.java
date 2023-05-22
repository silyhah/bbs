package com.sily.entity.enums;

public enum UpdateArticleCountTypeEnum {
    READ_COUNT(0,"阅读数"),
    GOOD_COUNT(1,"点赞数"),
    COMMENT_COUNT(2,"评论数");

    UpdateArticleCountTypeEnum(Integer updateType, String desc) {
        this.updateType = updateType;
        this.desc = desc;
    }

    public static UpdateArticleCountTypeEnum getType(Integer type){
        for (UpdateArticleCountTypeEnum item : UpdateArticleCountTypeEnum.values()){
            if (type.equals(item.getUpdateType())){
                return item;
            }
        }
        return null;
    }

    private Integer updateType;
    private String desc;

    public Integer getUpdateType() {
        return updateType;
    }

    public String getDesc() {
        return desc;
    }
}
