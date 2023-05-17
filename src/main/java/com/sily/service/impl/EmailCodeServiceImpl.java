package com.sily.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sily.common.BusinessException;
import com.sily.entity.EmailCode;
import com.sily.mapper.EmailCodeMapper;
import com.sily.service.IEmailCodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

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

    @Resource
    private IEmailCodeService iEmailCodeService;

    @Override
    public void checkCode(String email, String emailCode) {
        LambdaQueryWrapper<EmailCode> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EmailCode::getEmail,email)
                    .eq(EmailCode::getStatus,false);
        EmailCode one = iEmailCodeService.getOne(queryWrapper);
        if (!one.getCode().equals(emailCode)){
            throw new BusinessException("邮箱验证码错误");
        }
        one.setCreateTime(LocalDateTime.now());
        one.setStatus(true);
    }
}
