package com.sily.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sily.Utils.StringTools;
import com.sily.common.R;
import com.sily.entity.ForumComment;
import com.sily.entity.constants.Constants;
import com.sily.service.IForumCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author sily
 * @since 2023-04-24
 */
@RestController
public class ForumCommentController {

    @Autowired
    private IForumCommentService iForumCommentService;

    /**
     * 获取文章评论
     * @param articleId
     * @param pageNo
     * @param orderType
     * @return
     */
    @RequestMapping("/comment/loadComment")
    public R loadComment(String articleId, Integer pageNo, Integer orderType) {
        if (!StringTools.isEmpty(articleId)) {
            return R.error("文章id为空");
        }
        LambdaQueryWrapper<ForumComment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ForumComment::getArticleId, articleId);
        if (orderType == 0) {
            queryWrapper.orderByDesc(ForumComment::getGoodCount);
        } else if (orderType == 1) {
            queryWrapper.orderByDesc(ForumComment::getPostTime);
        }
        Page<ForumComment> page = new Page<>(pageNo, 15);
        return R.success(iForumCommentService.page(page, queryWrapper));
    }

    /**
     *
     * @param forumComment
     * @param session
     * @return
     */
    @RequestMapping("/comment/postComment")
    public R postComment(@RequestBody ForumComment forumComment, HttpSession session){
        forumComment.setUserId((String) session.getAttribute(Constants.USER_ID));
        if (!iForumCommentService.save(forumComment)) {
            return R.error("发布评论失败");
        }
        return R.success(forumComment);
    }

    @RequestMapping("/comment/doLike")
    public R doLike(){

        return null;
    }


}
