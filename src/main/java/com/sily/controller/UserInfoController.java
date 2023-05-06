package com.sily.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sily.Utils.StringTools;
import com.sily.common.CreateImageCode;
import com.sily.common.R;
import com.sily.entity.UserInfo;
import com.sily.entity.constants.Constants;
import com.sily.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
    public void checkCode(HttpServletResponse response, HttpSession session, Integer type) throws IOException {
        CreateImageCode checkCode = new CreateImageCode(130, 38, 5, 10);
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        String code = checkCode.getCode();
        if (type == null || type == 0) {
            session.setAttribute(Constants.CHECK_CODE_KEY, code);
        } else {
            session.setAttribute(Constants.CHECK_CODE_KEY_EMAIL, code);
        }
        checkCode.write(response.getOutputStream());
    }

    /**
     * 用户登录
     *
     * @param userInfo
     * @param code
     * @param session
     * @return
     */
    @PostMapping("/login")
    public R login(@RequestBody UserInfo userInfo, String code, HttpSession session) {
        String checkCode = (String) session.getAttribute(Constants.CHECK_CODE_KEY);
        if (!checkCode.equalsIgnoreCase(code)) {
            return R.error("验证码错误，请重新登录");
        }
        String password = DigestUtils.md5DigestAsHex(userInfo.getPassword().getBytes());
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(userInfo.getUserId() != null, UserInfo::getUserId, userInfo.getUserId())
                .eq(password != null, UserInfo::getPassword, password);
        UserInfo user = iUserInfoService.getOne(queryWrapper);
        if (user == null) {
            return R.error("用户名或者密码错误");
        }
        return R.success("登陆成功！");
    }

    /**
     * 发送邮件
     * @param session
     * @param email
     * @param checkCode
     * @param type
     * @return
     */
    @RequestMapping("/sendEmailCode")
    public R sendEmailCode(HttpSession session, String email, String checkCode, Integer type) {
        if (StringTools.isEmpty(email) || StringTools.isEmpty(checkCode) || type == null){
            return R.error("未填写全部");
        }
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("2664067940@qq.com");
            message.setTo(email);
            message.setSubject("邮箱验证码--bbs");
            message.setText("你的邮箱验证码是："+new CreateImageCode().getCode());
            mailSender.send(message);
            return R.success("发送成功");
        } catch (MailException e) {
            e.printStackTrace();
        }
        return R.error("发送失败");
    }
}
