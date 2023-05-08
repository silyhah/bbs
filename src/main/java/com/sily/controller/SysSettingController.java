package com.sily.controller;

import com.sily.common.R;
import com.sily.service.ISysSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 系统设置信息 前端控制器
 * </p>
 *
 * @author sily
 * @since 2023-04-24
 */
@RestController
public class SysSettingController {

    @Autowired
    private ISysSettingService iSysSettingService;

    /**
     * 获取系统设置
     * @return
     */
    @RequestMapping("/getSysSetting")
    public R getSysSetting(){
        return R.success(iSysSettingService.list());
    }

}
