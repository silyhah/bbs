package com.sily.service;

import com.sily.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sily.entity.enums.UserIntegralOperationTypeEnum;
import com.sily.entity.enums.UserIntegralTypeEnum;

/**
 * <p>
 * 用户信息 服务类
 * </p>
 *
 * @author sily
 * @since 2023-04-24
 */
public interface IUserInfoService extends IService<UserInfo> {

    void resetPwd(String email, String password, String emailCode);


    void register(String email,String nickName, String password,String emailCode);

    void updateUserIntegral(String userId, UserIntegralOperationTypeEnum userIntegralOperationTypeEnum, UserIntegralTypeEnum userIntegralTypeEnum, Integer integral);



}
