package com.sily.entity.vo;

import com.sily.entity.ForumArticle;
import com.sily.entity.ForumArticleAttachment;
import lombok.Data;

@Data
public class FormArticleDetailVo {

    private ForumArticle forumArticle;
    private Boolean haveLike;
    private ForumArticleAttachmentVo forumArticleAttachmentVo;





}
