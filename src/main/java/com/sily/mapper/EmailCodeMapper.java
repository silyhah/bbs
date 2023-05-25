package com.sily.mapper;

import com.sily.entity.EmailCode;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 邮箱验证码 Mapper 接口
 * </p>
 *
 * @author sily
 * @since 2023-04-24
 */
@Mapper
public interface EmailCodeMapper extends BaseMapper<EmailCode> {


    void disableEmailCode(@Param("email")String email);
}
