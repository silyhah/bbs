package com.sily.Utils;

public class StringTools {
    public static boolean isEmpty(String str){
        if (null==str || ""==str.trim() || "null"==str){
            return true;
        }
        return false;
    }
}
