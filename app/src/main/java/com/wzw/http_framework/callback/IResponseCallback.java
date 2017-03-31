package com.wzw.http_framework.callback;

/**
 * Http请求回调接口
 * Created by Henry on 2017/3/27.
 */

public interface IResponseCallback<T> {
    /**
     * 请求成功后回调
     * @param result
     */
    void onSuccess(T result);

    /**
     * 请求失败后回调
     * @param e
     */
    void onFailure(Exception e);
}
