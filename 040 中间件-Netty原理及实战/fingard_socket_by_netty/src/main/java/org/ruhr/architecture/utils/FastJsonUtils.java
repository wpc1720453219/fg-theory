package org.ruhr.architecture.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.lang.reflect.Type;


/**
 * 序列化.
 *
 * @author 阿飞
 * Created on 2020-10-26 13:54
 **/
public class FastJsonUtils {

    private static final SerializerFeature[] FEATURES = {
            // 输出空置字段
            SerializerFeature.WriteMapNullValue,
            // list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullListAsEmpty,
            // 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullNumberAsZero,
            // Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullBooleanAsFalse,
            // 字符类型字段如果为null，输出为""，而不是null
            SerializerFeature.WriteNullStringAsEmpty,
            //采用默认时间格式输出
            SerializerFeature.WriteDateUseDateFormat
    };

    public static <T> Object deserialize(String bytes, Class<?> c) throws Exception {
        if (bytes == null) {
            return null;
        }
        Object t1 = JSONObject.parseObject(bytes, Object.class);
        if (t1 instanceof JSONArray) {
            return JSON.parseArray(bytes, c);
        } else {
            return JSON.parseObject(bytes, c);

        }
    }

    public static <T> String serialize(T t) {
        SerializeConfig config = new SerializeConfig();
        config.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
        return JSON.toJSONString(t, config, FEATURES);
    }
}