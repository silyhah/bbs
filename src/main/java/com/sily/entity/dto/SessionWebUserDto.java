package com.sily.entity.dto;

public class SessionWebUserDto {


    private String nickName;
    private String province;
    private String userId;
    private Boolean isAdmin;

    @Override
    public String toString() {
        return "SessionWebUserDto{" +
                "nickName='" + nickName + '\'' +
                ", province='" + province + '\'' +
                ", userId='" + userId + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }
}
