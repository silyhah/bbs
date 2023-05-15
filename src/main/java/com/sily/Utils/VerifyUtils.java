package com.sily.Utils;

import com.sily.entity.enums.VerifyRegexEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerifyUtils {

    public static Boolean verify(String value, String regs){
        if (StringTools.isEmpty(value)){
            return false;
        }
        Pattern pattern = Pattern.compile(regs);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    public static Boolean verify(VerifyRegexEnum verifyRegexEnum,String value){
        return verify(value,verifyRegexEnum.getRegex());
    }

}
