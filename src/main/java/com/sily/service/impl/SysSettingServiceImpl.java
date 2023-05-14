package com.sily.service.impl;

import com.sily.Utils.JsonUtils;
import com.sily.Utils.StringTools;
import com.sily.Utils.SysCacheUtils;
import com.sily.entity.SysSetting;
import com.sily.entity.dto.SysSettingDto;
import com.sily.entity.enums.SysSettingEnum;
import com.sily.mapper.SysSettingMapper;
import com.sily.service.ISysSettingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    @Autowired
    private ISysSettingService iSysSettingService;

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
                PropertyDescriptor pd = new PropertyDescriptor(sysSettingEnum.getPropName(), SysSetting.class);
                Method method = pd.getWriteMethod();
                Class subClass = Class.forName(sysSettingEnum.gettClass());
                method.invoke(sysSettingDto, JsonUtils.convertJson2Obj(jsonContent, subClass));
            }
            SysCacheUtils.refresh(sysSettingDto);


        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
