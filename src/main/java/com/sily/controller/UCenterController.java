package com.sily.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sily.Utils.StringTools;
import com.sily.common.R;
import com.sily.entity.UserInfo;
import com.sily.entity.UserIntegralRecord;
import com.sily.entity.constants.Constants;
import com.sily.service.IUserInfoService;
import com.sily.service.IUserIntegralRecordService;
import com.sily.service.IUserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户中心 前端控制器
 * </p>
 *
 * @author sily
 * @since 2023-04-24
 */
@RestController
@RequestMapping("/ucenter")
public class UCenterController {

    @Autowired
    private IUserInfoService iUserInfoService;

    @Autowired
    private IUserIntegralRecordService iUserIntegralRecordService;

    @Autowired
    private IUserMessageService iUserMessageService;

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    @RequestMapping("/getUserInfo")
    public R getUserInfo(String userId){
        if (StringTools.isEmpty(userId)){
            return R.error("未填写");
        }
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserInfo::getUserId,userId);
        if (iUserInfoService.getOne(queryWrapper)==null){
            return R.error("查询失败");
        }
        return R.success(iUserInfoService.getOne(queryWrapper));
    }

    /**
     * 积分记录
     * @param pageNo
     * @param creatTimeStart
     * @param creatTimeEnd
     * @return
     */
    @RequestMapping("/loadUserIntegralRecord")
    public R loadUserIntegralRecord(Integer pageNo, LocalDateTime creatTimeStart, LocalDateTime creatTimeEnd){
        Page<UserIntegralRecord> page = new Page<>(pageNo,15);
        LambdaQueryWrapper<UserIntegralRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.between(UserIntegralRecord::getCreateTime,creatTimeStart,creatTimeEnd);
        if (iUserIntegralRecordService.page(page,queryWrapper)==null){
            return R.error("查询失败");
        }
        return R.success(iUserIntegralRecordService.page(page,queryWrapper));
    }

    /**
     * 修改个人信息
     * @param session
     * @param sex
     * @param personDescription
     * @param avatar
     * @return
     */
    @RequestMapping("/updateUserInfo")
    private R updateUserInfo(HttpSession session, Boolean sex, String personDescription, MultipartFile avatar){
        String userId = (String) session.getAttribute(Constants.USER_ID);
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserInfo::getUserId,userId);
        UserInfo one = iUserInfoService.getOne(queryWrapper);
        if (one ==null){
            return R.error("没有查询到");
        }
        one.setSex(sex);
        one.setPersonDescription(personDescription);
        return R.success(one);
    }

    @RequestMapping("/loadUserArticle")
    public R loadUserArticle(Integer userId, Integer type){
        return null;
    }

}
