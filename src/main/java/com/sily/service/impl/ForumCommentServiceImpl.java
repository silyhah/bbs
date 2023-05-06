package com.sily.service.impl;

import com.sily.entity.ForumComment;
import com.sily.mapper.ForumCommentMapper;
import com.sily.service.IForumCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author sily
 * @since 2023-04-24
 */
@Service
public class ForumCommentServiceImpl extends ServiceImpl<ForumCommentMapper, ForumComment> implements IForumCommentService {

}
