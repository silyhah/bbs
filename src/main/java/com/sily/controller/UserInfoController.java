package com.sily.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sily.Utils.SysCacheUtils;
import com.sily.annoation.GlobalInterceptor;
import com.sily.annoation.VerifyParam;
import com.sily.Exception.BusinessException;
import com.sily.common.CreateImageCode;
import com.sily.common.R;
import com.sily.entity.UserInfo;
import com.sily.entity.constants.Constants;
import com.sily.entity.dto.SessionWebUserDto;
import com.sily.entity.dto.SysSetting4CommentDto;
import com.sily.entity.dto.SysSettingDto;
import com.sily.entity.enums.CheckCodeTypeEnum;
import com.sily.entity.enums.VerifyRegexEnum;
import com.sily.service.IEmailCodeService;
import com.sily.service.IUserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * <p>
 * 用户信息 前端控制器
 * </p>
 *
 * @author sily
 * @since 2023-04-24
 */
@RestController
public class UserInfoController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    private IUserInfoService iUserInfoService;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private IEmailCodeService iEmailCodeService;


    /**
     * 获取验证码
     *
     * @param response
     * @param session
     * @param type
     * @throws IOException
     */
    @RequestMapping("/checkCode")
    public void checkCode(HttpServletResponse response, HttpSession session,
                          @RequestParam Integer type,
                          @RequestParam(required = false) Long time) throws IOException {
        CreateImageCode vCode = new CreateImageCode(130, 38, 5, 10);
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        String code = vCode.getCode();
        if (CheckCodeTypeEnum.CHECK_CODE_0.getType().equals(type)) {
            session.setAttribute(Constants.CHECK_CODE_KEY, code);
        } else {
            session.setAttribute(Constants.CHECK_CODE_KEY_EMAIL, code);
        }
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
    @GlobalInterceptor(checkParam = true)
    public R login(HttpSession session,
                   @VerifyParam(required = true, regex = VerifyRegexEnum.EMAIL) String email,
                   @VerifyParam(required = true) String password,
                   @VerifyParam(required = true) String checkCode) {
        try {
            if (!checkCode.equalsIgnoreCase((String) session.getAttribute(Constants.CHECK_CODE_KEY))) {
                throw new BusinessException("图片验证码错误");
            }
            SessionWebUserDto sessionWebUserDto = iUserInfoService.login(email, password);
            session.setAttribute(Constants.SESSION_KEY, sessionWebUserDto);
        } finally {
            session.removeAttribute(Constants.CHECK_CODE_KEY);
        }
        return R.success("登陆成功");
    }


    /**
     * 获取用户信息
     * @param session
     * @return
     */
    @RequestMapping("/getUserInfo")
    public R getUserInfo(HttpSession session) {
        return R.success(getUser(session));
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
    @GlobalInterceptor
    public R sendEmailCode(HttpSession session,
                           @VerifyParam(required = true, regex = VerifyRegexEnum.EMAIL) String email,
                           @VerifyParam(required = true) String checkCode,
                           @VerifyParam(required = true) Integer type) {
        if (!checkCode.equals(session.getAttribute(Constants.CHECK_CODE_KEY))) {
            throw new BusinessException("验证码错误");
        }
        iEmailCodeService.sendEmailCode(email, type);
        return R.success("发送成功");
    }

    /**
     * 注册
     *
     * @param session
     * @param email
     * @param nickName
     * @param password
     * @param emailCode
     * @param checkCode
     * @return
     */
    @PostMapping("/register")
    @GlobalInterceptor(checkParam = true)
    public R register(HttpSession session,
                      @VerifyParam(required = true, regex = VerifyRegexEnum.EMAIL) String email,
                      @VerifyParam(required = true, regex = VerifyRegexEnum.NUMBER_LETTER_UNDER_LINE) String nickName,
                      @VerifyParam(required = true, regex = VerifyRegexEnum.PASSWORD) String password,
                      @VerifyParam(required = true, regex = VerifyRegexEnum.PASSWORD) String emailCode,
                      @VerifyParam(required = true, regex = VerifyRegexEnum.PASSWORD) String checkCode) {
        try {
            if (!session.getAttribute(Constants.CHECK_CODE_KEY).equals(checkCode)) {
                throw new BusinessException("图片验证码错误");
            }
            this.iUserInfoService.register(email, nickName, password, emailCode);
        } catch (Exception e) {
            logger.error("注册失败", e);
            throw new BusinessException("注册失败");
        }
        throw new BusinessException("注册失败");
    }

    /**
     * 重置密码
     *
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
            iUserInfoService.resetPwd(email, password, emailCode);

        } finally {
            session.removeAttribute(Constants.CHECK_CODE_KEY);
        }
        return R.success(null);
    }


    /**
     * 退出登录
     *
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public R logout(HttpSession session) {
        session.invalidate();
        return R.success(null);
    }


    /**
     * 获取系统设置
     * @return
     */
    @RequestMapping("/getSysSetting")
    public R getSysSetting() {
        SysSettingDto sysSetting = SysCacheUtils.getSysSetting();
        SysSetting4CommentDto commentSetting = sysSetting.getCommentSetting();
        Map<String,Object> map = new HashMap<>();
        map.put("commentOpen",commentSetting);
        return R.success(map);
    }

}
