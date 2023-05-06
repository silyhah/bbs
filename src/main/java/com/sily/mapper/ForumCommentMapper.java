package com.sily.mapper;

import com.sily.entity.ForumComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 评论 Mapper 接口
 * </p>
 *
 * @author sily
 * @since 2023-04-24
 */
@Mapper
public interface ForumCommentMapper extends BaseMapper<ForumComment> {

}
