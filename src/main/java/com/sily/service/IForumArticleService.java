package com.sily.service;

import com.sily.entity.ForumArticle;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 文章信息 服务类
 * </p>
 *
 * @author sily
 * @since 2023-04-24
 */
public interface IForumArticleService extends IService<ForumArticle> {

    ForumArticle readArticle(String articleId);

}
