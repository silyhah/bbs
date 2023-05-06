package com.sily.service.impl;

import com.sily.entity.ForumBoard;
import com.sily.mapper.ForumBoardMapper;
import com.sily.service.IForumBoardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章板块信息 服务实现类
 * </p>
 *
 * @author sily
 * @since 2023-04-24
 */
@Service
public class ForumBoardServiceImpl extends ServiceImpl<ForumBoardMapper, ForumBoard> implements IForumBoardService {

}
