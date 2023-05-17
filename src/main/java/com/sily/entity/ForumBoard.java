package com.sily.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 文章板块信息
 * </p>
 *
 * @author sily
 * @since 2023-04-24
 */
@TableName("forum_board")
@Data
public class ForumBoard implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 板块ID
     */
    @TableId(value = "board_id", type = IdType.AUTO)
    private Integer boardId;

    /**
     * 父级板块ID
     */
    private Integer pBoardId;

    /**
     * 板块名
     */
    private String boardName;

    /**
     * 封面
     */
    private String cover;

    /**
     * 描述
     */
    private String boardDesc;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 0:只允许管理员发帖 1:任何人可以发帖
     */
    private Boolean postType;

    @TableField(exist = false)
    private List<ForumBoard> children;

    @Override
    public String toString() {
        return "ForumBoard{" +
                "boardId=" + boardId +
                ", pBoardId=" + pBoardId +
                ", boardName='" + boardName + '\'' +
                ", cover='" + cover + '\'' +
                ", boardDesc='" + boardDesc + '\'' +
                ", sort=" + sort +
                ", postType=" + postType +
                ", children=" + children +
                '}';
    }

    public List<ForumBoard> getChildren() {
        return children;
    }

    public void setChildren(List<ForumBoard> children) {
        this.children = children;
    }


    public Integer getBoardId() {
        return boardId;
    }

    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }

    public Integer getpBoardId() {
        return pBoardId;
    }

    public void setpBoardId(Integer pBoardId) {
        this.pBoardId = pBoardId;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getBoardDesc() {
        return boardDesc;
    }

    public void setBoardDesc(String boardDesc) {
        this.boardDesc = boardDesc;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getPostType() {
        return postType;
    }

    public void setPostType(Boolean postType) {
        this.postType = postType;
    }

}
