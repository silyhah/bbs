package com.sily.mapper;

import com.sily.entity.UserMessage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户消息 Mapper 接口
 * </p>
 *
 * @author sily
 * @since 2023-04-24
 */
@Mapper
public interface UserMessageMapper extends BaseMapper<UserMessage> {

}
