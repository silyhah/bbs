package com.sily.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sily.Utils.JsonUtils;
import com.sily.Utils.StringTools;
import com.sily.Utils.SysCacheUtils;
import com.sily.common.BusinessException;
import com.sily.common.R;
import com.sily.entity.UserInfo;
import com.sily.entity.UserIntegralRecord;
import com.sily.entity.UserMessage;
import com.sily.entity.constants.Constants;
import com.sily.entity.enums.MessageStatusEnum;
import com.sily.entity.enums.MessageTypeEnum;
import com.sily.entity.enums.UserIntegralOperationTypeEnum;
import com.sily.entity.enums.UserIntegralTypeEnum;
import com.sily.mapper.UserInfoMapper;
import com.sily.mapper.UserIntegralRecordMapper;
import com.sily.service.IEmailCodeService;
import com.sily.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

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

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private UserIntegralRecordMapper userIntegralRecordMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resetPwd(String email, String password, String emailCode) {
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserInfo::getEmail, email);
        UserInfo userInfo = iUserInfoService.getOne(queryWrapper);
        if (userInfo == null) {
            throw new BusinessException("用户不存在");
        }
        iEmailCodeService.checkCode(email, emailCode);
        userInfo.setPassword(password);
        iUserInfoService.updateById(userInfo);
    }

    @Override
    public void register(String email, String nickName, String password, String emailCode) {
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserInfo::getEmail, email);
        UserInfo user = iUserInfoService.getOne(queryWrapper);
        if (user != null) {
            throw new BusinessException("邮箱已存在");
        }
        LambdaQueryWrapper<UserInfo> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(UserInfo::getNickName, nickName);
        UserInfo user1 = iUserInfoService.getOne(queryWrapper1);
        if (user1 != null) {
            throw new BusinessException("昵称已存在");
        }
        this.iEmailCodeService.checkCode(email, emailCode);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(StringTools.getRandomString(Constants.LENGTH_10));
        userInfo.setEmail(email);
        userInfo.setNickName(nickName);
        userInfo.setPassword(DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8)));
        userInfo.setLastLoginTime(LocalDateTime.now());
        userInfo.setStatus(1);
        userInfo.setJoinTime(LocalDateTime.now());


        updateUserIntegral(userInfo.getUserId(), UserIntegralOperationTypeEnum.REGISTER, UserIntegralTypeEnum.PLUS, Constants.INTEGRAL_5);

        UserMessage userMessage = new UserMessage();
        userMessage.setReceivedUserId(userInfo.getUserId());
        userMessage.setMessageType(MessageTypeEnum.SYS.getType());
        userMessage.setMessageContent(SysCacheUtils.getSysSetting().getRegisterSetting().getRegisterWelcomInfo());
        userMessage.setStatus(MessageStatusEnum.NO_READ.getType());
        userMessage.setCreateTime(LocalDateTime.now());

    }


    /**
     * 更新用户积分
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateUserIntegral(String userId, UserIntegralOperationTypeEnum userIntegralOperationTypeEnum, UserIntegralTypeEnum userIntegralTypeEnum,  Integer integral) {
        Integer changeType = userIntegralTypeEnum.getType();
        integral = integral * changeType;
        if (changeType == 0) {
            return;
        }
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserInfo::getUserId, userId);
        UserInfo userInfo = userInfoMapper.selectOne(queryWrapper);
        Integer currentIntegral = userInfo.getCurrentIntegral();
        if (UserIntegralTypeEnum.MINUS.getType().equals(changeType) && currentIntegral + integral < 0) {
            integral = userInfo.getCurrentIntegral() * changeType;
        }
        UserIntegralRecord userIntegralRecord = new UserIntegralRecord();
        userIntegralRecord.setUserId(userId);
        userIntegralRecord.setOperType(userIntegralOperationTypeEnum.getType());
        userIntegralRecord.setIntegral(integral);
        userIntegralRecord.setCreateTime(LocalDateTime.now());
        this.userIntegralRecordMapper.insert(userIntegralRecord);

        Integer count = this.userInfoMapper.updateUserIntegral(userId, integral);
        if (count<=0){
            throw new BusinessException("用户更新积分失败");
        }
    }


}
