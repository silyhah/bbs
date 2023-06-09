package com.sily.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 文章信息
 * </p>
 *
 * @author sily
 * @since 2023-04-24
 */
@TableName("forum_article")
@Data
public class ForumArticle implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章ID
     */
    private String articleId;

    /**
     * 板块ID
     */
    private Integer boardId;

    /**
     * 板块名称
     */
    private String boardName;

    /**
     * 父级板块ID
     */
    private Integer pBoardId;

    /**
     * 父板块名称
     */
    private String pBoardName;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 最后登录ip地址
     */
    private String userIpAddress;

    /**
     * 标题
     */
    private String title;

    /**
     * 封面
     */
    private String cover;

    /**
     * 内容
     */
    private String content;

    /**
     * markdown内容
     */
    private String markdownContent;

    /**
     * 0:富文本编辑器 1:markdown编辑器
     */
    private Integer editorType;

    /**
     * 摘要
     */
    private String summary;

    /**
     * 发布时间
     */
    private LocalDateTime postTime;

    /**
     * 最后更新时间
     */
    private LocalDateTime lastUpdateTime;

    /**
     * 阅读数量
     */
    private Integer readCount;

    /**
     * 点赞数
     */
    private Integer goodCount;

    /**
     * 评论数
     */
    private Integer commentCount;

    /**
     * 0未置顶  1:已置顶
     */
    private Integer topType;

    /**
     * 0:没有附件  1:有附件
     */
    private Integer attachmentType;

    /**
     * -1已删除 0:待审核  1:已审核
     */
    private Integer status;


    public ForumArticle(String articleId, Integer boardId, String boardName, Integer pBoardId, String pBoardName, String userId, String nickName, String userIpAddress, String title, String cover, String content, String markdownContent, Integer editorType, String summary, LocalDateTime postTime, LocalDateTime lastUpdateTime, Integer readCount, Integer goodCount, Integer commentCount, Integer topType, Integer attachmentType, Integer status) {
        this.articleId = articleId;
        this.boardId = boardId;
        this.boardName = boardName;
        this.pBoardId = pBoardId;
        this.pBoardName = pBoardName;
        this.userId = userId;
        this.nickName = nickName;
        this.userIpAddress = userIpAddress;
        this.title = title;
        this.cover = cover;
        this.content = content;
        this.markdownContent = markdownContent;
        this.editorType = editorType;
        this.summary = summary;
        this.postTime = postTime;
        this.lastUpdateTime = lastUpdateTime;
        this.readCount = readCount;
        this.goodCount = goodCount;
        this.commentCount = commentCount;
        this.topType = topType;
        this.attachmentType = attachmentType;
        this.status = status;
    }



    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public Integer getBoardId() {
        return boardId;
    }

    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public Integer getpBoardId() {
        return pBoardId;
    }

    public void setpBoardId(Integer pBoardId) {
        this.pBoardId = pBoardId;
    }

    public String getpBoardName() {
        return pBoardName;
    }

    public void setpBoardName(String pBoardName) {
        this.pBoardName = pBoardName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserIpAddress() {
        return userIpAddress;
    }

    public void setUserIpAddress(String userIpAddress) {
        this.userIpAddress = userIpAddress;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMarkdownContent() {
        return markdownContent;
    }

    public void setMarkdownContent(String markdownContent) {
        this.markdownContent = markdownContent;
    }

    public Integer getEditorType() {
        return editorType;
    }

    public void setEditorType(Integer editorType) {
        this.editorType = editorType;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public LocalDateTime getPostTime() {
        return postTime;
    }

    public void setPostTime(LocalDateTime postTime) {
        this.postTime = postTime;
    }

    public LocalDateTime getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(LocalDateTime lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public Integer getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(Integer goodCount) {
        this.goodCount = goodCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getTopType() {
        return topType;
    }

    public void setTopType(Integer topType) {
        this.topType = topType;
    }

    public Integer getAttachmentType() {
        return attachmentType;
    }

    public void setAttachmentType(Integer attachmentType) {
        this.attachmentType = attachmentType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ForumArticle{" +
        "articleId=" + articleId +
        ", boardId=" + boardId +
        ", boardName=" + boardName +
        ", pBoardId=" + pBoardId +
        ", pBoardName=" + pBoardName +
        ", userId=" + userId +
        ", nickName=" + nickName +
        ", userIpAddress=" + userIpAddress +
        ", title=" + title +
        ", cover=" + cover +
        ", content=" + content +
        ", markdownContent=" + markdownContent +
        ", editorType=" + editorType +
        ", summary=" + summary +
        ", postTime=" + postTime +
        ", lastUpdateTime=" + lastUpdateTime +
        ", readCount=" + readCount +
        ", goodCount=" + goodCount +
        ", commentCount=" + commentCount +
        ", topType=" + topType +
        ", attachmentType=" + attachmentType +
        ", status=" + status +
        "}";
    }
}
