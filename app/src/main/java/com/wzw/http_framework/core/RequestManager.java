package com.wzw.http_framework.core;

import android.support.v4.util.ArrayMap;

import java.util.ArrayList;

/**
 * Http请求管理类
 * Created by Henry on 2017/3/23.
 */

public class RequestManager {
    private static RequestManager sRequestManager;
    private ArrayMap<String, ArrayList<RequestTask>> mExecutedTask;
    private RequestManager(){
        mExecutedTask = new ArrayMap<>();
    }
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
    public void performRequest(CommonRequest request){
        RequestTask task = new RequestTask(request);
        task.execute();
        if(!mExecutedTask.containsKey(request.getTag())){
            ArrayList<RequestTask> tasks = new ArrayList<>();
            mExecutedTask.put(request.getTag(),tasks);
        }
        mExecutedTask.get(request.getTag()).add(task);
    }

    public void cancelRequest(String tag){
        if(tag == null || tag.equals("")){
            return;
        }
        if(mExecutedTask.containsKey(tag)){
            ArrayList<RequestTask> tasks = mExecutedTask.get(tag);
            for (RequestTask task: tasks) {
                if(!task.isCancelled() && !task.isCompleted()){
                    task.cancel();
                }
            }
        }
    }

}
