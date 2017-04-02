package com.wzw.http_framework.core;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.wzw.http_framework.HttpApplication;
import com.wzw.http_framework.exception.HttpException;
import com.wzw.http_framework.protocol.DataMode;
import com.wzw.http_framework.util.LogUtil;
import com.wzw.http_framework.util.NetWorkUtil;

import org.json.JSONObject;

import java.util.concurrent.atomic.AtomicBoolean;


/**
 * Http异步任务
 * Created by Henry on 2017/3/30.
 */

public class RequestTask implements Runnable {
    private static final String TAG = RequestTask.class.getSimpleName();
    private static InternalHandler sHandler;
    private CommonRequest mRequest;
    private final AtomicBoolean mCancelled = new AtomicBoolean();
    private boolean isCache;
    private boolean isCompleted;

    public RequestTask(CommonRequest request) {
        this.mRequest = request;
    }


    public boolean isCompleted() {
        return isCompleted;
    }

    public void execute() {
        CoreExecutorService.getExecutorService().submit(this);
    }

    public final void cancel() {
        mCancelled.set(true);
        CoreExecutorService.cancel(this);
    }

    public boolean isCancelled() {
        return mCancelled.get();
    }

    @Override
    public void run() {
        if (isCancelled()) {
            onFailure(new HttpException(HttpException.ErrorType.CANCEL, "the request has been cancelled"));
            LogUtil.e(TAG, "the request has been cancelled");
            return;
        }
        String json;
        try {
            if (!NetWorkUtil.isConnect(HttpApplication.getContext()) &&
                    (mRequest.getDataMode() == DataMode.DATA_FROM_NET_NEED_CACHE ||
                            mRequest.getDataMode() == DataMode.DATA_FROM_NET_NO_CACHE)) {
                //断网处理
                onFailure(new HttpException(HttpException.ErrorType.IO, "network disconnect"));
                return;
            }
            switch (mRequest.getDataMode()) {
                // 访问网络，不做本地存储
                case DATA_FROM_NET_NO_CACHE:
                    json = DataUtil.getJsonFromServer(mRequest);
                    isCache = false;
                    break;
                // 仅访问本地存储
                case DATA_FROM_CACHE:
                    // cache的数据永远正确
                    json = DataUtil.getJsonFromCache(mRequest.getUrl());
                    break;
                // 先访问本地存储返回数据展示,再访问网络更新数据并刷新UI,
                case DATA_FROM_NET_UPDATE_CACHE:
                    json = DataUtil.getJsonFromServer(mRequest);
                    if (!TextUtils.isEmpty(json)) {
                        isCache = true;
                    } else {
                        json = DataUtil.getJsonFromCache(mRequest.getUrl());
                    }
                    break;
                case DATA_FROM_NET_NEED_CACHE:
                    json = DataUtil.getJsonFromServer(mRequest);
                    isCache = true;
                    break;
                // 默认访问网络，正常情况不会执行default
                default:
                    json = DataUtil.getJsonFromServer(mRequest);
                    isCache = false;
                    break;
            }
            LogUtil.i(TAG, json);
            if (!TextUtils.isEmpty(json)) {
                JSONObject jsonObject = new JSONObject(json);
                if (jsonObject.has("code")) {
                    int code = jsonObject.optInt("code");
                    if (code == 0) {
                        Object o = mRequest.getResponseCallback().bindData(json);
                        onSuccess(o);
                        isCompleted = true;
                        //成功才能缓存
                        if (isCache) {
                            DataUtil.cacheJson(mRequest.getUrl(), json);
                        }
                    } else {
                        //code不为0
                        onFailure(new HttpException(HttpException.ErrorType.JSON, "json error"));
                    }
                } else {
                    onFailure(new HttpException(HttpException.ErrorType.JSON, "json error"));
                }
            } else {
                //Json为空
                onFailure(new HttpException(HttpException.ErrorType.SERVER, "json empty"));
            }
        } catch (Exception e) {
            LogUtil.e(TAG, "error---" + e.toString());
            e.printStackTrace();
            onFailure(e);
        }

    }

    private void onFailure(final Exception e) {
        getHandler().post(new Runnable() {
            @Override
            public void run() {
                mRequest.getResponseCallback().onFailure(e);
            }
        });
    }

    private void onSuccess(final Object o) {
        getHandler().post(new Runnable() {
            @Override
            public void run() {
                mRequest.getResponseCallback().onSuccess(o);
            }
        });
    }

    private static class InternalHandler extends Handler {
        public InternalHandler() {
            super(Looper.getMainLooper());
        }
    }

    private static InternalHandler getHandler() {
        if (sHandler == null) {
            synchronized (RequestTask.class) {
                if (sHandler == null) {
                    sHandler = new InternalHandler();
                }
            }
        }
        return sHandler;
    }

}
