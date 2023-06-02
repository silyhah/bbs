package com.sily.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sily.Exception.BusinessException;
import com.sily.entity.ForumArticle;
import com.sily.entity.constants.Constants;
import com.sily.entity.enums.ArticleStatusEnum;
import com.sily.entity.enums.UpdateArticleCountTypeEnum;
import com.sily.mapper.ForumArticleMapper;
import com.sily.service.IForumArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章信息 服务实现类
 * </p>
 *
 * @author sily
 * @since 2023-04-24
 */
@Service
public class ForumArticleServiceImpl extends ServiceImpl<ForumArticleMapper, ForumArticle> implements IForumArticleService {



    @Autowired
    private IForumArticleService iForumArticleService;

    @Autowired
    private ForumArticleMapper forumArticleMapper;




    public ForumArticle readArticle(String articleId){
        LambdaQueryWrapper<ForumArticle> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ForumArticle::getArticleId,articleId);
        ForumArticle forumArticle = iForumArticleService.getOne(queryWrapper);
        if (forumArticle==null){
            throw new BusinessException("404");
        }
        if (ArticleStatusEnum.REVIEWED.getStatus().equals(forumArticle.getStatus())){
            forumArticleMapper.updateArticleCount(UpdateArticleCountTypeEnum.READ_COUNT.getUpdateType(), Constants.ONE,articleId);
        }
        return forumArticle;
    }
}
