package com.sily.service;

import com.sily.entity.EmailCode;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 邮箱验证码 服务类
 * </p>
 *
 * @author sily
 * @since 2023-04-24
 */
public interface IEmailCodeService extends IService<EmailCode> {

    void checkCode(String email, String emailCode);

}
