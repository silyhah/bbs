package com.sily.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户积分记录表
 * </p>
 *
 * @author sily
 * @since 2023-04-24
 */
@TableName("user_integral_record")
@Data
public class UserIntegralRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录ID
     */
    @TableId(value = "record_id", type = IdType.AUTO)
    private Integer recordId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 操作类型
     */
    private Integer operType;

    /**
     * 积分
     */
    private Integer integral;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getOperType() {
        return operType;
    }

    public void setOperType(Integer operType) {
        this.operType = operType;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserIntegralRecord{" +
        "recordId=" + recordId +
        ", userId=" + userId +
        ", operType=" + operType +
        ", integral=" + integral +
        ", createTime=" + createTime +
        "}";
    }
}
