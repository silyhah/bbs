package com.sily.service;

import com.sily.entity.ForumBoard;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 文章板块信息 服务类
 * </p>
 *
 * @author sily
 * @since 2023-04-24
 */
public interface IForumBoardService extends IService<ForumBoard> {

    List<ForumBoard> getBoardTree(Integer postType);

}
