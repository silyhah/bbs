package com.sily.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户附件下载
 * </p>
 *
 * @author sily
 * @since 2023-04-24
 */
@TableName("forum_article_attachment_download")
@Data
public class ForumArticleAttachmentDownload implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文件ID
     */
    private String fileId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 文章ID
     */
    private String articleId;

    /**
     * 文件下载次数
     */
    private Integer downloadCount;


    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    @Override
    public String toString() {
        return "ForumArticleAttachmentDownload{" +
        "fileId=" + fileId +
        ", userId=" + userId +
        ", articleId=" + articleId +
        ", downloadCount=" + downloadCount +
        "}";
    }
}
