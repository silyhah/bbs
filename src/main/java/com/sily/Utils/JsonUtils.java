package com.sily.Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.util.ObjectUtils;

import java.util.List;

public class JsonUtils {

    public static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);


    /**
     * 对象转json
     * @param obj
     * @return
     */
    public static String ConvertObj2Json(Object obj){
        return JSON.toJSONString(obj);
    }

    /**
     * 将json转变成字符串
     * @param json
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T>T convertJson2Obj(String json,Class<T> tClass){
        return JSONObject.parseObject(json,tClass);
    }

    /**
     * 字符串数组转集合对象
     * @param json
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> List<T> convertJsonArray2List(String json, Class<T> tClass){
        return JSONArray.parseArray(json,tClass);
    }
}
