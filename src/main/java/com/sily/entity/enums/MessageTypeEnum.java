package com.sily.entity.enums;

public enum MessageTypeEnum {
    SYS(0,"sys","X系统消息"),
    COMMENT(1,"comment","评论"),
    ARTICLE_LIKE(2,"articleLike","文章点赞"),
    COMMENT_LIKE(3,"commentLike","评论点赞"),
    DOWNLOAD_ATTACHMENT(4,"downLoad","附件下载");


    private Integer type;
    private String code;
    private String desc;

    MessageTypeEnum(Integer type, String code, String desc) {
        this.type = type;
        this.code = code;
        this.desc = desc;
    }

    public MessageTypeEnum getMessageTypeEnum(Integer type){
        for (MessageTypeEnum item: MessageTypeEnum.values()){
            if (item.getType().equals(type)){
                return item;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public Integer getType() {
        return type;
    }



    public String getDesc() {
        return desc;
    }


}
