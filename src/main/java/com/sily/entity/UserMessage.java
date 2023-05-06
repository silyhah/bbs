package com.sily.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户消息
 * </p>
 *
 * @author sily
 * @since 2023-04-24
 */
@TableName("user_message")
@Data
public class UserMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增ID
     */
    @TableId(value = "message_id", type = IdType.AUTO)
    private Integer messageId;

    /**
     * 接收人用户ID
     */
    private String receivedUserId;

    /**
     * 文章ID
     */
    private String articleId;

    /**
     * 文章标题
     */
    private String articleTitle;

    /**
     * 评论ID
     */
    private Integer commentId;

    /**
     * 发送人用户ID
     */
    private String sendUserId;

    /**
     * 发送人昵称
     */
    private String sendNickName;

    /**
     * 0:系统消息 1:评论 2:文章点赞  3:评论点赞 4:附件下载
     */
    private Integer messageType;

    /**
     * 消息内容
     */
    private String messageContent;

    /**
     * 1:未读 2:已读
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getReceivedUserId() {
        return receivedUserId;
    }

    public void setReceivedUserId(String receivedUserId) {
        this.receivedUserId = receivedUserId;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(String sendUserId) {
        this.sendUserId = sendUserId;
    }

    public String getSendNickName() {
        return sendNickName;
    }

    public void setSendNickName(String sendNickName) {
        this.sendNickName = sendNickName;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserMessage{" +
        "messageId=" + messageId +
        ", receivedUserId=" + receivedUserId +
        ", articleId=" + articleId +
        ", articleTitle=" + articleTitle +
        ", commentId=" + commentId +
        ", sendUserId=" + sendUserId +
        ", sendNickName=" + sendNickName +
        ", messageType=" + messageType +
        ", messageContent=" + messageContent +
        ", status=" + status +
        ", createTime=" + createTime +
        "}";
    }
}
