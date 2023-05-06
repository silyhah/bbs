package com.sily.service.impl;

import com.sily.entity.EmailCode;
import com.sily.mapper.EmailCodeMapper;
import com.sily.service.IEmailCodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 邮箱验证码 服务实现类
 * </p>
 *
 * @author sily
 * @since 2023-04-24
 */
@Service
public class EmailCodeServiceImpl extends ServiceImpl<EmailCodeMapper, EmailCode> implements IEmailCodeService {

}
