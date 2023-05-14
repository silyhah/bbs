package com.sily.entity.enums;

public enum SysSettingEnum {

    AUDIT("audit","com.sily.entity.dto.SysSetting4AuditDto","auditSetting","审核设置"),
    COMMENT("comment","com.sily.entity.dto.SysSetting4CommentDto","commentSetting","评论设置"),
    POST("post","com.sily.entity.dto.SysSetting4PostDto","postSetting","帖子设置"),
    LIKE("like","com.sily.entity.dto.SysSetting4LikeDto","likeSetting","点赞设置"),
    REGISTER("register","com.sily.entity.dto.SysSetting4RegisterDto","registerSetting","注册设置"),
    EMAIL("email","com.sily.entity.dto.SysSetting4EmailDto","emailSetting","邮件设置");



    private String code;
    private String tClass;
    private String propName;
    private String desc;

    SysSettingEnum(String code, String tClass, String propName, String desc){
        this.code=code;
        this.tClass=tClass;
        this.propName=propName;
        this.desc=desc;
    }

    public static SysSettingEnum getByCode(String code){
        for (SysSettingEnum item : SysSettingEnum.values()){
            if (item.getCode().equals(code)){
                return item;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String gettClass() {
        return tClass;
    }

    public String getPropName() {
        return propName;
    }

    public String getDesc() {
        return desc;
    }
}
