package com.sily.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sily.annoation.GlobalInterceptor;
import com.sily.annoation.VerifyParam;
import com.sily.common.BusinessException;
import com.sily.common.CreateImageCode;
import com.sily.common.R;
import com.sily.entity.UserInfo;
import com.sily.entity.constants.Constants;
import com.sily.entity.enums.VerifyRegexEnum;
import com.sily.service.IUserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;


/**
 * <p>
 * 用户信息 前端控制器
 * </p>
 *
 * @author sily
 * @since 2023-04-24
 */
@RestController
public class UserInfoController {

    private static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    private IUserInfoService iUserInfoService;

    @Autowired
    private JavaMailSender mailSender;


    /**
     * 获取验证码
     *
     * @param response
     * @param session
     * @param type
     * @throws IOException
     */
    @RequestMapping("/checkCode")
    public void checkCode(HttpServletResponse response, HttpSession session, Integer type, Long time) throws IOException {
        CreateImageCode vCode = new CreateImageCode(130, 38, 5, 10);
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        String checkCode = vCode.getCode();
        session.setAttribute(Constants.CHECK_CODE_KEY, checkCode);
        vCode.write(response.getOutputStream());
    }

    /**
     * 用户登录
     *
     * @param email
     * @param password
     * @param checkCode
     * @param session
     * @return
     */
    @RequestMapping("/login")
    public R login(HttpSession session,
                   @VerifyParam(required = true, regex = VerifyRegexEnum.EMAIL) String email,
                   @VerifyParam(required = true, regex = VerifyRegexEnum.PASSWORD) String password,
                   @VerifyParam(required = true) String checkCode) {
        try {
            if (!checkCode.equalsIgnoreCase((String) session.getAttribute(Constants.CHECK_CODE_KEY))) {
                throw new BusinessException("图片验证码错误");
            }
            LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(UserInfo::getEmail, email);
            UserInfo userInfo = iUserInfoService.getOne(queryWrapper);
            if (userInfo == null||!password.equals(userInfo.getPassword())) {
                throw new BusinessException("用户名或者密码错误");
            }
            if (userInfo.getStatus().equals(Constants.STATUS_0)){
                throw new BusinessException("账号被禁用");
            }
        } finally {
            session.removeAttribute(Constants.CHECK_CODE_KEY);
        }
        return R.success("登录成功");
    }

    /**
     * 发送邮件
     *
     * @param session
     * @param email
     * @param checkCode
     * @param type
     * @return
     */
    @RequestMapping("/sendEmailCode")
    @GlobalInterceptor(checkParam = true)
    public R sendEmailCode(HttpSession session,
                           @VerifyParam(required = true, regex = VerifyRegexEnum.EMAIL) String email,
                           @VerifyParam(required = true) String checkCode,
                           @VerifyParam(required = true) Integer type) {
        try {
            String emailCode = new CreateImageCode().getCode();
            session.setAttribute(Constants.CHECK_CODE_KEY_EMAIL, emailCode);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("2664067940@qq.com");
            message.setTo(email);
            message.setSubject("邮箱验证码--bbs");
            message.setText("你的邮箱验证码是：" + emailCode);
            mailSender.send(message);
            return R.success("发送成功");
        } catch (MailException e) {
            e.printStackTrace();
        }
        return R.error("发送失败");
    }

    /**
     * 注册
     * @param session
     * @param email
     * @param nickName
     * @param password
     * @param emailCode
     * @param checkCode
     * @return
     */
    @PostMapping("/register")
    @GlobalInterceptor
    public R register(HttpSession session,
                      @VerifyParam(required = true, regex = VerifyRegexEnum.EMAIL) String email,
                      @VerifyParam(required = true,regex = VerifyRegexEnum.NUMBER_LETTER_UNDER_LINE) String nickName,
                      @VerifyParam(required = true,regex = VerifyRegexEnum.PASSWORD) String password,
                      @VerifyParam(required = true,regex = VerifyRegexEnum.PASSWORD) String emailCode,
                      @VerifyParam(required = true,regex = VerifyRegexEnum.PASSWORD) String checkCode) {
        try {
            if (!emailCode.equalsIgnoreCase((String) session.getAttribute(Constants.CHECK_CODE_KEY_EMAIL))) {
                return R.error("邮箱验证码错误");
            }
            if (!checkCode.equalsIgnoreCase((String) session.getAttribute(Constants.CHECK_CODE_KEY))) {
                return R.error("图片验证码错误");
            }
            UserInfo userInfo = new UserInfo();
            userInfo.setEmail(email);
            userInfo.setNickName(nickName);
            userInfo.setPassword(password);
            userInfo.setLastLoginTime(LocalDateTime.now());
            userInfo.setStatus(1);
            userInfo.setJoinTime(LocalDateTime.now());
        } catch (Exception e) {
            logger.error("注册失败",e);
            throw new BusinessException("注册失败");
        }
        throw new BusinessException("注册失败");
    }

    /**
     * 重置密码
     * @param email
     * @param emailCode
     * @param password
     * @param checkCode
     * @param session
     * @return
     */
    @RequestMapping("/resetPwd")
    @GlobalInterceptor(checkParam = true)
    public R resetPwd(HttpSession session,
                      @VerifyParam(required = true, regex = VerifyRegexEnum.EMAIL) String email,
                      @VerifyParam(required = true) String emailCode,
                      @VerifyParam(required = true, regex = VerifyRegexEnum.PASSWORD) String password,
                      @VerifyParam(required = true) String checkCode) {
        try {
            if (!checkCode.equalsIgnoreCase((String) session.getAttribute(Constants.CHECK_CODE_KEY))) {
                session.removeAttribute(Constants.CHECK_CODE_KEY);
                return R.error("图片验证码错误");
            }
            iUserInfoService.resetPwd(email,password,emailCode);

        } finally {
            session.removeAttribute(Constants.CHECK_CODE_KEY);
        }
        return R.success(null);
    }

    /**
     * 获取用户信息
     * @param session
     * @return
     */
    @RequestMapping("/getUserInfo")
    public R getUserInfo(HttpSession session){
        String userId = (String) session.getAttribute(Constants.USER_ID);
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserInfo::getUserId,userId);
        UserInfo user = iUserInfoService.getOne(queryWrapper);
        return user==null ? R.error("获取信息失败") : R.success(user);
    }

    /**
     * 退出登录
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public R logout(HttpSession session){
        session.removeAttribute(Constants.USER_ID);
        return R.success("退出成功");
    }

}
