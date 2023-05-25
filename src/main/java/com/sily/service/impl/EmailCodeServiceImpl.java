package com.sily.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sily.Utils.StringTools;
import com.sily.common.BusinessException;
import com.sily.config.WebConfig;
import com.sily.entity.EmailCode;
import com.sily.entity.constants.Constants;
import com.sily.entity.dto.SysSettingDto;
import com.sily.entity.enums.EmailCodeEnum;
import com.sily.mapper.EmailCodeMapper;
import com.sily.service.IEmailCodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sily.service.ISysSettingService;
import com.sily.service.SysSettingDtoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.List;

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

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private WebConfig webMvcConfig;

    @Autowired
    private EmailCodeMapper emailCodeMapper;

    @Autowired
    private ISysSettingService iSysSettingService;

    private static final Logger logger = LoggerFactory.getLogger(EmailCodeServiceImpl.class);




    @Override
    public void checkCode(String email, String emailCode) {
        LambdaQueryWrapper<EmailCode> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EmailCode::getEmail, email)
                .eq(EmailCode::getStatus, false);
        EmailCode one = iEmailCodeService.getOne(queryWrapper);
        if (!one.getCode().equals(emailCode)) {
            throw new BusinessException("邮箱验证码错误");
        }
        one.setCreateTime(LocalDateTime.now());
        one.setStatus(true);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sendEmailCode(String email, Integer type) {
        /*if (EmailCodeEnum.EMAIL_CODE_0.getType().equals(type)) {
            LambdaQueryWrapper<EmailCode> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(EmailCode::getEmail,email);
            EmailCode one = iEmailCodeService.getOne(queryWrapper);
            if(one!=null){
                throw new BusinessException("用户已存在");
            }
        }*/
        String code = StringTools.getRandomString(Constants.FIVE);
        sendEmail(email,code);
        emailCodeMapper.disableEmailCode(email);

        EmailCode emailCode = new EmailCode();
        emailCode.setEmail(email);
        emailCode.setCode(code);
        emailCode.setStatus(false);
        emailCode.setCreateTime(LocalDateTime.now());
        iEmailCodeService.save(emailCode);


    }


    private void sendEmail(String toEmail, String code){
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(webMvcConfig.getSendUserName());
            message.setTo(toEmail);
            SysSettingDto sysSettingDto = iSysSettingService.getSysSettingDto();
            String emailTitle = sysSettingDto.getEmailSetting().getEmailTitle();
            message.setSubject(emailTitle);
            String emailContent = sysSettingDto.getEmailSetting().getEmailContent();
            message.setText(String.format(emailContent,code));
            mailSender.send(message);
        } catch (MailException e) {
            logger.error("邮件发送失败",e);
            throw new BusinessException("邮件发送失败");
        }
    }


}
