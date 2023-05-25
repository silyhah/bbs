package com.sily.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class WebConfig extends AppConfig {


    @Value("${spring.mail.username:}")
    private String sendUserName;

    public String getSendUserName() {
        return sendUserName;
    }
}
