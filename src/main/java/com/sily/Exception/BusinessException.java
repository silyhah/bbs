package com.sily.Exception;

import com.sily.entity.enums.ResponseCodeEnum;

public class BusinessException extends RuntimeException{

    private Integer responseCode;
    private String desc;

    public BusinessException(String message){
        super(message);
    }


    public BusinessException(ResponseCodeEnum responseCodeEnum){
        this.responseCode=responseCodeEnum.getResponseCode();
        this.desc=responseCodeEnum.getDesc();
    }
}
