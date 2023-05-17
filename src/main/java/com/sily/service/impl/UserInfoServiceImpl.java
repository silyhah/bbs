package com.sily.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sily.common.BusinessException;
import com.sily.common.R;
import com.sily.entity.UserInfo;
import com.sily.entity.constants.Constants;
import com.sily.mapper.UserInfoMapper;
import com.sily.service.IEmailCodeService;
import com.sily.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author sily
 * @since 2023-04-24
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {


    @Resource
    private IUserInfoService iUserInfoService;

    @Resource
    private IEmailCodeService iEmailCodeService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resetPwd(String email, String password, String emailCode) {
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserInfo::getEmail, email);
        UserInfo userInfo = iUserInfoService.getOne(queryWrapper);
        if (userInfo == null) {
            throw new BusinessException("用户不存在");
        }
        iEmailCodeService.checkCode(email,emailCode);
        userInfo.setPassword(password);
        iUserInfoService.updateById(userInfo);
    }
}
