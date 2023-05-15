package com.sily.entity.dto;

import lombok.Data;

@Data
public class SysSetting4AuditDto {

    private Boolean commentAudit;

    private Boolean postAudit;

    public Boolean getCommentAudit() {
        return commentAudit;
    }

    public void setCommentAudit(Boolean commentAudit) {
        this.commentAudit = commentAudit;
    }

    public Boolean getPostAudit() {
        return postAudit;
    }

    public void setPostAudit(Boolean postAudit) {
        this.postAudit = postAudit;
    }
}
