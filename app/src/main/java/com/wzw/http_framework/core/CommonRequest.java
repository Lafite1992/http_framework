package com.wzw.http_framework.core;

import com.wzw.http_framework.callback.AbsResponseCallback;
import com.wzw.http_framework.protocol.DataMode;
import com.wzw.http_framework.protocol.HttpMethod;

import java.util.Map;

/**
 * Http请求参数封装类
 * Created by Henry on 2017/3/30.
 */

public class CommonRequest {
    private AbsResponseCallback mResponseCallback;
    private String mUrl;
    private DataMode mDataMode = DataMode.DATA_FROM_NET_NO_CACHE;
    private HttpMethod mMethod = HttpMethod.GET;
    private Map<String, String> mPostParameters;
    private Map<String,String> mHeaders;
    private String mTag;

    private CommonRequest(Builder builder) {
        this.mUrl = builder.mUrl;
        this.mDataMode = builder.mDataMode;
        this.mHeaders = builder.mHeaders;
        this.mMethod = builder.mMethod;
        this.mPostParameters = builder.mPostParameters;
        this.mResponseCallback = builder.mResponseCallback;
        this.mTag = builder.mTag;
    }


    public String getUrl() {
        return mUrl;
    }


    public DataMode getDataMode() {
        return mDataMode;
    }

    public Map<String, String> getHeaders() {
        return mHeaders;
    }

    public Map<String, String> getPostParameters() {
        return mPostParameters;
    }

    public HttpMethod getMethod() {
        return mMethod;
    }

    public String getTag() {
        return mTag;
    }

    public AbsResponseCallback getResponseCallback() {
        return mResponseCallback;
    }
    public static class Builder{
        private AbsResponseCallback mResponseCallback;
        private String mUrl;
        private DataMode mDataMode = DataMode.DATA_FROM_NET_NO_CACHE;
        private HttpMethod mMethod = HttpMethod.GET;
        private Map<String, String> mPostParameters;
        private Map<String,String> mHeaders;
        private String mTag;


        public Builder setUrl(String url) {
            this.mUrl = url;
            return this;
        }

        public Builder setDataMode(DataMode dataMode) {
            this.mDataMode = dataMode;
            return this;
        }

        public Builder setHeaders(Map<String, String> headers) {
            this.mHeaders = headers;
            return this;
        }

        public Builder setMethod(HttpMethod method) {
            this.mMethod = method;
            return this;
        }

        public Builder setPostParameters(Map<String, String> postParameters) {
            this.mPostParameters = postParameters;
            return this;
        }

        public Builder setResponseCallback(AbsResponseCallback responseCallback) {
            this.mResponseCallback = responseCallback;
            return this;
        }

        public Builder setTag(String tag) {
            this.mTag = tag;
            return this;
        }

        public CommonRequest build(){
            return new CommonRequest(this);
        }
    }
}
