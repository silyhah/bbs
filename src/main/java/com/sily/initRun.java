package com.sily;

import com.sily.service.ISysSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class initRun implements ApplicationRunner {

    @Autowired
    private ISysSettingService iSysSettingService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        iSysSettingService.refresh();
    }
}
