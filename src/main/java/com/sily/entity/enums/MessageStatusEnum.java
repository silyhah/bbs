package com.sily.entity.enums;

public enum MessageStatusEnum {
    NO_READ(0,"未读"),
    READ(1,"已读");


    private Integer type;
    private String desc;



    public MessageStatusEnum getMessageStatusEnum(Integer type){
        for (MessageStatusEnum item : MessageStatusEnum.values()){
            if (item.getType().equals(type)){
                return item;
            }
        }
        return null;
    }

    MessageStatusEnum(Integer type, String desc) {
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
