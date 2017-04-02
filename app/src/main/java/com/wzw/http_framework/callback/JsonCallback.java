package com.wzw.http_framework.callback;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 解析json
 * Created by Henry on 2017/3/27.
 */

public abstract class JsonCallback<T> extends AbsResponseCallback<T> {
    @Override
    public T bindData(String json) {
        Type type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return new Gson().fromJson(json, type);
    }
}
