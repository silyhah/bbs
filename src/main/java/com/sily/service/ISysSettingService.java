package com.sily.service;

import com.sily.entity.SysSetting;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sily.entity.dto.SysSettingDto;

/**
 * <p>
 * 系统设置信息 服务类
 * </p>
 *
 * @author sily
 * @since 2023-04-24
 */
public interface ISysSettingService extends IService<SysSetting> {

    void refresh();

    SysSettingDto getSysSettingDto();
}
