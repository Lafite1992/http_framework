package com.wzw.http_framework.callback;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Henry on 2017/3/27.
 */

public abstract class JsonCallback<T> extends AbsResponseCallback<T> {
    @Override
    public T bindData(Object data) {
        Type type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        T t = new Gson().fromJson(data.toString(), type);
        return t;
    }
}
