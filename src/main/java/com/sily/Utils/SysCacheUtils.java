package com.sily.Utils;

import com.sily.entity.dto.SysSettingDto;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SysCacheUtils {

    private static final String KEY_SYS = "key_setting";
    private static final Map<String, SysSettingDto> CACHE_DATA = new ConcurrentHashMap<>();


    public static SysSettingDto getSysSetting(){
        return CACHE_DATA.get(KEY_SYS);
    }

    public static void refresh(SysSettingDto dto){
        CACHE_DATA.put(KEY_SYS,dto);
    }
}
