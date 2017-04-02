package com.wzw.http_framework.sample;

import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;

import com.wzw.http_framework.CommonBean;
import com.wzw.http_framework.R;
import com.wzw.http_framework.callback.JsonCallback;
import com.wzw.http_framework.core.CommonRequest;
import com.wzw.http_framework.core.RequestManager;
import com.wzw.http_framework.protocol.HttpMethod;
import com.wzw.http_framework.util.LogUtil;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testGet();

    }

    private void testGet() {
        CommonRequest request = new CommonRequest.Builder()
                .setUrl("https://t.zm.gaiay.cn/api/zm/live/view/auth/?appOs=android&appVersion=5.9.20&liveId=31074&token=640a0c9aa77f5d341c323f782c322062&userId=34d1bc1545c152a1a-7ea3&circleId=c1efa3159915b02d4-7ffe")
                .setTag(MainActivity.class.getSimpleName())
                .setResponseCallback(new JsonCallback<CommonBean>() {

                    @Override
                    public void onSuccess(CommonBean result) {
                        LogUtil.i("TAG",result.getResult().toString());
                    }

                    @Override
                    public void onFailure(Exception e) {
                        e.printStackTrace();
                    }
                }).build();
        RequestManager.getRequestManager().performRequest(request);
    }
    private void testPost() {
        ArrayMap<String, String> params = new ArrayMap<>(4);
        params.put("conditionId","282");
        params.put("userId","34d1bc1545c152a1a-7ea3");
        params.put("price","0.01");
        params.put("conditionState","");
        CommonRequest request = new CommonRequest.Builder()
                .setUrl("https://t.zm.gaiay.cn/api/w/sf/c1efa3159915b02d4-7ffe/condition/condition/?appVersion=5.9.20&appOs=android&token=640a0c9aa77f5d341c323f782c322062&userId=34d1bc1545c152a1a-7ea3")
                .setPostParameters(params)
                .setMethod(HttpMethod.POST)
                .setResponseCallback(new JsonCallback<CommonBean>() {

                    @Override
                    public void onSuccess(CommonBean result) {
                        LogUtil.i("TAG",result.getMessage());
                    }

                    @Override
                    public void onFailure(Exception e) {
                        e.printStackTrace();
                    }
                }).build();
        RequestManager.getRequestManager().performRequest(request);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //根据tag取消当前activity的所有请求
        RequestManager.getRequestManager().cancelRequest(MainActivity.class.getSimpleName());
    }
}
