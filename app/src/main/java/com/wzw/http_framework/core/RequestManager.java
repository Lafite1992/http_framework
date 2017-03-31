package com.wzw.http_framework.core;

/**
 * Http请求管理类
 * Created by Henry on 2017/3/23.
 */

public class RequestManager {
    private static RequestManager sRequestManager;
    public static RequestManager getRequestManager(){
        if(sRequestManager == null){
            synchronized (RequestManager.class){
                if(sRequestManager == null){
                    sRequestManager = new RequestManager();
                }
            }
        }
        return sRequestManager;
    }
    public RequestTask buildTask(CommonRequest request){
        RequestTask task = new RequestTask(request);
        return task;
    }

}
