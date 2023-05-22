package com.sily.mapper;

import com.sily.entity.ForumArticle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 文章信息 Mapper 接口
 * </p>
 *
 * @author sily
 * @since 2023-04-24
 */
@Mapper
public interface ForumArticleMapper extends BaseMapper<ForumArticle> {


    void updateArticleCount(@Param("updateType") Integer updateType, @Param("changeCount")Integer changeCount,
                            @Param("articleId")String articleId);
}
