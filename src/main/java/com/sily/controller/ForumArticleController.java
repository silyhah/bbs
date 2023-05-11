package com.sily.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sily.Utils.StringTools;
import com.sily.common.R;
import com.sily.entity.ForumArticle;
import com.sily.entity.ForumBoard;
import com.sily.entity.constants.Constants;
import com.sily.service.IForumArticleService;
import com.sily.service.IForumBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.xml.ws.Action;

/**
 * <p>
 * 文章信息 前端控制器
 * </p>
 *
 * @author sily
 * @since 2023-04-24
 */
@RestController
public class ForumArticleController {

    @Autowired
    private IForumArticleService iForumArticleService;

    @Autowired
    private IForumBoardService iForumBoardService;

    /**
     * 获取文章列表
     *
     * @param pBoardId
     * @param boardId
     * @param orderType
     * @param pageNo
     * @return
     */
    @RequestMapping("/forum/loadArticle")
    public R loadArticle(Integer pBoardId, Integer boardId, Integer orderType, Integer pageNo) {
        LambdaQueryWrapper<ForumArticle> queryWrapper = new LambdaQueryWrapper<>();
        if (pBoardId != null) {
            queryWrapper.eq(ForumArticle::getpBoardId, pBoardId);
        }
        if (boardId != null) {
            queryWrapper.eq(ForumArticle::getBoardId, boardId);
        }
        if (orderType == 0) {
            queryWrapper.orderByDesc(ForumArticle::getGoodCount);
        } else if (orderType == 1) {
            queryWrapper.orderByDesc(ForumArticle::getPostTime);
        } else if (orderType == 2) {
            queryWrapper.orderByAsc(ForumArticle::getPostTime);
        }
        Page<ForumArticle> page = new Page<>(pageNo, 15);
        return R.success(iForumArticleService.page(page, queryWrapper));
    }


    /**
     * 获取文章详情
     *
     * @param articleId
     * @return
     */
    @RequestMapping("/forum/getArticleDetail")
    public R getArticleDetail(String articleId) {
        if (StringTools.isEmpty(articleId)) {
            return R.error("没有查到");
        }
        LambdaQueryWrapper<ForumArticle> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ForumArticle::getArticleId, articleId);
        return R.success(iForumArticleService.getOne(queryWrapper));
    }

    /**
     * 点赞文章
     *
     * @param articleId
     * @return
     */
    @RequestMapping("/forum/doLike")
    public R doLike(String articleId) {
        if (StringTools.isEmpty(articleId)) {
            return R.error("没有查到");
        }
        LambdaQueryWrapper<ForumArticle> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ForumArticle::getArticleId, articleId);
        ForumArticle one = iForumArticleService.getOne(queryWrapper);
        one.setGoodCount(one.getGoodCount() + 1);
        return R.success("点赞成功");
    }

    /**
     * 获取用户下载信息
     *
     * @param session
     * @param fileId
     * @return
     */
    @RequestMapping("/forum/getUserDownloadInfo")
    public R getUserDownloadInfo(HttpSession session, String fileId) {
        String userId = (String) session.getAttribute(Constants.USER_ID);
        LambdaQueryWrapper<ForumArticle> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ForumArticle::getUserId, userId);
        ForumArticle one = iForumArticleService.getOne(queryWrapper);
        if (one.getAttachmentType() == 0) {
            return R.success("没有附件");
        } else {
            return R.success(true);
        }
    }

    @RequestMapping("/forum/attachmentDownload")
    public R attachmentDownload(String fileId) {
        if (StringTools.isEmpty(fileId)) {
            return R.error("没有这个文件");
        }
        return null;

    }

    /**
     * 发布文章获取板块
     *
     * @return
     */
    @RequestMapping("/forum/loadBoard4Post")
    public R loadBoard4Post() {
        LambdaQueryWrapper<ForumBoard> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ForumBoard::getpBoardId, 0);
        return R.success(iForumBoardService.list(queryWrapper));

    }

    /**
     * 发布文章
     * @param forumArticle
     * @return
     */
    @RequestMapping("/forum/postArticle")
    public R postArticle(@RequestBody ForumArticle forumArticle) {
        if ((forumArticle.getEditorType() == 1 && StringTools.isEmpty(forumArticle.getMarkdownContent())) || forumArticle.getpBoardId() == null || StringTools.isEmpty(forumArticle.getTitle()) || StringTools.isEmpty(forumArticle.getContent())) {
            return R.error("内容不能为空");
        }
        iForumArticleService.save(forumArticle);
        return R.success("发布成功");
    }

    /**
     * 修改文章
     * @param articleId
     * @return
     */
    @RequestMapping("/forum/articleDetail4Update")
    public R updateArticleDetail(String articleId){
        if (StringTools.isEmpty(articleId)){
            return R.error("文章id为空");
        }
        LambdaQueryWrapper<ForumArticle> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ForumArticle::getArticleId,articleId);
        ForumArticle one = iForumArticleService.getOne(queryWrapper);
        return R.success(one);

    }

    /**
     * 修改文章
     * @param forumArticle
     * @return
     */
    @RequestMapping("/forum/updateArticle")
    public R updateArticle(@RequestBody ForumArticle forumArticle){
        if ((forumArticle.getEditorType() == 1 && StringTools.isEmpty(forumArticle.getMarkdownContent())) || forumArticle.getpBoardId() == null || StringTools.isEmpty(forumArticle.getTitle()) || StringTools.isEmpty(forumArticle.getContent())||forumArticle.getAttachmentType()==null) {
            return R.error("内容不能为空");
        }
        LambdaQueryWrapper<ForumArticle> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ForumArticle::getArticleId,forumArticle.getArticleId());
        if (!iForumArticleService.update(forumArticle,queryWrapper)){
            return R.error("修改失败");
        }
        return R.success("修改成功");
    }


    /**
     * 搜索
     * @param keyword
     * @return
     */
    @RequestMapping("/forum/search")
    public R search(String keyword){
        Page<ForumArticle> page = new Page<>(1,15);
        LambdaQueryWrapper<ForumArticle> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(ForumArticle::getTitle,keyword).eq(ForumArticle::getSummary,keyword);
        return R.success(iForumArticleService.page(page,queryWrapper));
    }

}
