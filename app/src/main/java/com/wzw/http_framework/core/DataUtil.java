package com.wzw.http_framework.core;

import com.wzw.http_framework.db.HttpCache;
import com.wzw.http_framework.protocol.HttpMethod;
import com.wzw.http_framework.util.LogUtil;
import com.wzw.http_framework.util.MD5Util;

import org.json.JSONException;

import java.util.Map;

/**
 * Json处理工具类
 * Created by Henry on 2017/3/26.
 */

public class DataUtil {
    private static final String TAG = DataUtil.class.getSimpleName();

    /**
     * Http请求数据
     * @param request
     * @return
     * @throws JSONException
     */
    public static String getJsonFromServer(CommonRequest request) throws JSONException {
        String url = request.getUrl();
        LogUtil.i(TAG, "getJsonFromServer " + request.getUrl());
        HttpMethod method = request.getMethod();
        Map<String, String> postParameters = request.getPostParameters();
        String json;
        if (method == HttpMethod.POST){
            json = HttpUtil.post(url, postParameters);
        } else if (method == HttpMethod.PUT){
            json = HttpUtil.put(url, postParameters);
        } else if (method == HttpMethod.DELETE){
            json = HttpUtil.delete(url, postParameters);
        } else {
            Map<String, String> headers = request.getHeaders();
            if (headers != null && headers.size()> 0) {
                json = HttpUtil.get(url,headers);
            }else{
                json = HttpUtil.get(url);
            }
        }
        return json;
    }

    /**
     * 从本地数据库中读取数据
     * @param url
     * @return
     */
    public static String getJsonFromCache(String url) {
        LogUtil.i(TAG, "getJsonFromCache" + url );
        return HttpCache.getCache().get(MD5Util.md5(url));
    }

    /**
     * 将数据保存到数据库
     * @param url
     * @param json
     */
    public static void cacheJson(String url, String json) {
        LogUtil.i(TAG, "cacheJson" + url);
        HttpCache.getCache().put(MD5Util.md5(url), json);
        LogUtil.i(TAG, "cacheJson end");
    }
}
