package com.sily.Utils;

import org.apache.commons.lang3.RandomStringUtils;

public class StringTools {
    public static boolean isEmpty(String str){
        if (null==str || ""==str.trim() || "null"==str){
            return true;
        }
        return false;
    }

    public static String getRandomString(Integer number){
        return RandomStringUtils.random(number,true,true);
    }

    public static String getSuffixFile(String file){
        return file.substring(file.lastIndexOf("."));
    }
}
