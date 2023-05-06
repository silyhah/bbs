package com.sily.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 点赞记录
 * </p>
 *
 * @author sily
 * @since 2023-04-24
 */
@TableName("like_record")
@Data
public class LikeRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增ID
     */
    @TableId(value = "op_id", type = IdType.AUTO)
    private Integer opId;

    /**
     * 操作类型0:文章点赞 1:评论点赞
     */
    private Integer opType;

    /**
     * 主体ID
     */
    private String objectId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 发布时间
     */
    private LocalDateTime createTime;

    /**
     * 主体作者ID
     */
    private String authorUserId;


    public Integer getOpId() {
        return opId;
    }

    public void setOpId(Integer opId) {
        this.opId = opId;
    }

    public Integer getOpType() {
        return opType;
    }

    public void setOpType(Integer opType) {
        this.opType = opType;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getAuthorUserId() {
        return authorUserId;
    }

    public void setAuthorUserId(String authorUserId) {
        this.authorUserId = authorUserId;
    }

    @Override
    public String toString() {
        return "LikeRecord{" +
        "opId=" + opId +
        ", opType=" + opType +
        ", objectId=" + objectId +
        ", userId=" + userId +
        ", createTime=" + createTime +
        ", authorUserId=" + authorUserId +
        "}";
    }
}
