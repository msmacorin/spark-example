package br.com.macorin.spark.domain.utils;

import com.google.gson.Gson;

import spark.ResponseTransformer;

public class JsonUtil {

    private JsonUtil(){}

    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }

    public static <T> T toObject(String json, Class<T> clazz) {
        return new Gson().fromJson(json, clazz);
    }

    public static ResponseTransformer json() {
        return JsonUtil::toJson;
    }
}
