package com.sily.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sily.Utils.StringTools;
import com.sily.annoation.GlobalInterceptor;
import com.sily.annoation.VerifyParam;
import com.sily.common.BusinessException;
import com.sily.common.R;
import com.sily.entity.ForumArticle;
import com.sily.entity.ForumArticleAttachment;
import com.sily.entity.ForumBoard;
import com.sily.entity.constants.Constants;
import com.sily.entity.enums.ArticleOrderTypeEnum;
import com.sily.entity.enums.ArticleStatusEnum;
import com.sily.entity.enums.VerifyRegexEnum;
import com.sily.entity.vo.FormArticleDetailVo;
import com.sily.mapper.ForumArticleMapper;
import com.sily.service.IForumArticleAttachmentService;
import com.sily.service.IForumArticleService;
import com.sily.service.IForumBoardService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.xml.ws.Action;
import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    private IForumArticleAttachmentService iForumArticleAttachmentService;




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
    public R loadArticle(Integer pBoardId, Integer boardId,
                         @RequestHeader(defaultValue = "0") Integer orderType,
                         @RequestHeader(defaultValue = "1") Integer pageNo) {
        LambdaQueryWrapper<ForumArticle> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(pBoardId!=null,ForumArticle::getpBoardId, pBoardId)
                .eq(boardId!=null, ForumArticle::getBoardId, boardId);

        if (orderType.equals(ArticleOrderTypeEnum.HOT.getType())){
            queryWrapper.orderByDesc(ForumArticle::getTopType)
                    .orderByAsc(ForumArticle::getReadCount)
                    .orderByAsc(ForumArticle::getGoodCount)
                    .orderByAsc(ForumArticle::getCommentCount);
        }else if(orderType.equals(ArticleOrderTypeEnum.NEW.getType())){
            queryWrapper.orderByAsc(ForumArticle::getPostTime);
        }else{
            queryWrapper.orderByDesc(ForumArticle::getPostTime);
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
    public R getArticleDetail(@VerifyParam(required = true) String articleId) {
        ForumArticle forumArticle = iForumArticleService.readArticle(articleId);

        if (ArticleStatusEnum.REVIEW.getStatus().equals(forumArticle.getStatus())||
        ArticleStatusEnum.DELETE.getStatus().equals(forumArticle.getStatus())){
            throw new BusinessException("404");
        }
        FormArticleDetailVo formArticleDetailVo = new FormArticleDetailVo();
        formArticleDetailVo.setForumArticle(forumArticle);
        if (forumArticle.getStatus().equals(Constants.ONE)){
            LambdaQueryWrapper<ForumArticleAttachment> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(ForumArticleAttachment::getArticleId,articleId);
            ForumArticleAttachment forumArticleAttachment = iForumArticleAttachmentService.getOne(queryWrapper);
            BeanUtils.copyProperties(formArticleDetailVo.getForumArticleAttachmentVo(),forumArticleAttachment);
        }
        return R.success(formArticleDetailVo);
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
     *
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
     *
     * @param articleId
     * @return
     */
    @RequestMapping("/forum/articleDetail4Update")
    public R updateArticleDetail(String articleId) {
        if (StringTools.isEmpty(articleId)) {
            return R.error("文章id为空");
        }
        LambdaQueryWrapper<ForumArticle> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ForumArticle::getArticleId, articleId);
        ForumArticle one = iForumArticleService.getOne(queryWrapper);
        return R.success(one);

    }

    /**
     * 修改文章
     *
     * @param forumArticle
     * @return
     */
    @RequestMapping("/forum/updateArticle")
    public R updateArticle(@RequestBody ForumArticle forumArticle) {
        if ((forumArticle.getEditorType() == 1 && StringTools.isEmpty(forumArticle.getMarkdownContent())) || forumArticle.getpBoardId() == null || StringTools.isEmpty(forumArticle.getTitle()) || StringTools.isEmpty(forumArticle.getContent()) || forumArticle.getAttachmentType() == null) {
            return R.error("内容不能为空");
        }
        LambdaQueryWrapper<ForumArticle> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ForumArticle::getArticleId, forumArticle.getArticleId());
        if (!iForumArticleService.update(forumArticle, queryWrapper)) {
            return R.error("修改失败");
        }
        return R.success("修改成功");
    }


    /**
     * 搜索
     *
     * @param keyword
     * @return
     */
    @RequestMapping("/forum/search")
    public R search(String keyword) {
        Page<ForumArticle> page = new Page<>(1, 15);
        LambdaQueryWrapper<ForumArticle> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(ForumArticle::getTitle, keyword).eq(ForumArticle::getSummary, keyword);
        return R.success(iForumArticleService.page(page, queryWrapper));
    }

}
