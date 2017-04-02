package com.wzw.http_framework.callback;



/**
 *
 * Created by Henry on 2017/3/28.
 */

public abstract class AbsResponseCallback<T> implements IResponseCallback<T> {
    public abstract T bindData(String result);

}
