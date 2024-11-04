package com.yjs.utls;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONReader;
import lombok.extern.slf4j.Slf4j;

/**
 * @Classname JsonUtils
 * @Description
 * @Version 1.0.0
 * @Date 2024/04/09 09:37
 * @Created by Enzuo
 */

public class JsonUtils {
    public static String toJson(Object obj) {
        return JSON.toJSONString(obj);
    }

    public static <T> T toInstance(Object obj, Class<T> clazz) {
        String json = toJson(obj);
        return toInstance(json, clazz);
    }

    public static <T> T toInstance(String jsonString, Class<T> clazz) {
        return JSONObject.parseObject(jsonString, clazz, JSONReader.Feature.SupportSmartMatch);
    }


}
