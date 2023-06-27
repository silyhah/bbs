package com.sily.entity.enums;



/**
 * 枚举类
 *
 * @author sily
 */
public enum ArticleOrderTypeEnum {




    HOT(0,"按热度"),
    NEW(1,"按最新"),
    SEND(2,"发布");

    private Integer type;
    private String desc;

    ArticleOrderTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }



    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public static ArticleOrderTypeEnum getType(Integer type){
        for (ArticleOrderTypeEnum item: ArticleOrderTypeEnum.values()){
            if (item.getType().equals(type)){
                return item;
            }
        }
        return null;
    }
}
