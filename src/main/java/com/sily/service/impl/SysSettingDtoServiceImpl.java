package com.sily.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sily.entity.dto.SysSettingDto;
import com.sily.mapper.SysSettingDtoMapper;
import com.sily.service.SysSettingDtoService;
import org.springframework.stereotype.Service;

@Service
public class SysSettingDtoServiceImpl extends ServiceImpl<SysSettingDtoMapper, SysSettingDto>implements SysSettingDtoService {
}
