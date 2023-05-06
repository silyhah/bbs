package com.sily.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 邮箱验证码
 * </p>
 *
 * @author sily
 * @since 2023-04-24
 */
@TableName("email_code")
public class EmailCode implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 编号
     */
    private String code;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 0:未使用  1:已使用
     */
    private Boolean status;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "EmailCode{" +
        "email=" + email +
        ", code=" + code +
        ", createTime=" + createTime +
        ", status=" + status +
        "}";
    }
}
