package com.sily.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sily.Utils.JsonUtils;
import com.sily.Utils.StringTools;
import com.sily.Utils.SysCacheUtils;
import com.sily.entity.SysSetting;
import com.sily.entity.dto.SysSettingDto;
import com.sily.entity.enums.SysSettingEnum;
import com.sily.mapper.SysSettingDtoMapper;
import com.sily.mapper.SysSettingMapper;
import com.sily.service.ISysSettingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sily.service.SysSettingDtoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.List;

/**
 * <p>
 * 系统设置信息 服务实现类
 * </p>
 *
 * @author sily
 * @since 2023-04-24
 */
@Service
public class SysSettingServiceImpl extends ServiceImpl<SysSettingMapper, SysSetting> implements ISysSettingService {


    @Resource
    private ISysSettingService iSysSettingService;

    private static final Logger logger = LoggerFactory.getLogger(SysSettingServiceImpl.class);

    @Override
    public void refresh() {
        try {
            SysSettingDto sysSettingDto = new SysSettingDto();
            List<SysSetting> list = this.iSysSettingService.list();
            for (SysSetting sysSetting : list) {
                String jsonContent = sysSetting.getJsonContent();
                if (StringTools.isEmpty(jsonContent)) {
                    continue;
                }
                String code = sysSetting.getCode();
                SysSettingEnum sysSettingEnum = SysSettingEnum.getByCode(code);
                PropertyDescriptor pd = new PropertyDescriptor(sysSettingEnum.getPropName(), SysSettingDto.class);
                Method method = pd.getWriteMethod();
                Class subClass = Class.forName(sysSettingEnum.gettClass());
                method.invoke(sysSettingDto, JsonUtils.convertJson2Obj(jsonContent, subClass));
            }
            SysCacheUtils.refresh(sysSettingDto);


        } catch (Exception e) {
            logger.error("刷新缓存失败");

        }
    }
}
