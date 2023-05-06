package com.sily.service.impl;

import com.sily.entity.ForumArticle;
import com.sily.mapper.ForumArticleMapper;
import com.sily.service.IForumArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
