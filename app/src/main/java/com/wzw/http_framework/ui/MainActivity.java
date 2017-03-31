package com.wzw.http_framework.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wzw.http_framework.CommonBean;
import com.wzw.http_framework.R;
import com.wzw.http_framework.callback.JsonCallback;
import com.wzw.http_framework.core.CommonRequest;
import com.wzw.http_framework.core.RequestManager;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CommonRequest request = new CommonRequest.Builder()
                .setUrl("")
                .setResponseCallback(new JsonCallback<ArrayList<CommonBean>>() {

                    @Override
                    public void onSuccess(ArrayList<CommonBean> result) {

                    }

                    @Override
                    public void onFailure(Exception e) {

                    }
                }).build();
        RequestManager.getRequestManager().buildTask(request).execute();

    }
}
