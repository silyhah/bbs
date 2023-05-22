package com.sily.entity.enums;

public enum ArticleStatusEnum {
    DELETE(-1,"被删除"),
    REVIEW(0,"待审核"),
    REVIEWED(1,"已审核");

    private Integer status;
    private String desc;

    ArticleStatusEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public static ArticleStatusEnum getByType(Integer type){
        for (ArticleStatusEnum item : ArticleStatusEnum.values()){
            if (item.getStatus().equals(type)){
                return item;
            }
        }
        return null;
    }

    public Integer getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }
}
