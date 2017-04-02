package com.wzw.http_framework.core;

import android.net.Uri;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.wzw.http_framework.util.LogUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Http请求工具类
 * Created by Henry on 2017/3/26.
 */

public class HttpUtil {
    private static OkHttpClient sOkHttpClient;

    public static String get(String url) {
        LogUtil.e("HttpUtil", url);
        Request request= new Request.Builder()
                .url(url)
                .build();
        Response response;
        try {
            response = getOkHttpClient().newCall(request).execute();
            String body = response.body().string();
            if (response.isSuccessful()) {
                return body;
            }
        } catch (IOException e) {
            LogUtil.e("HttpUtil", e.toString());
        }
        return "";


    }

    public static String get(String url, Map<String,String> headers ) {
        LogUtil.e("HttpUtil", url);
        Request request;
        Request.Builder builder = new Request.Builder();
        if (headers.size() > 0){
            for (Object o : headers.entrySet()) {
                Map.Entry entry = (Map.Entry) o;
                String key = (String) entry.getKey();
                String value = (String) entry.getValue();
                builder.addHeader(key, value);
            }
        }
        request = builder.url(url).build();
        Response response;
        try {
            response = getOkHttpClient().newCall(request).execute();

            String body = response.body().string();
            if (response.isSuccessful()) {
                return body;
            }
        } catch (IOException e) {
            LogUtil.e("HttpUtil", e.toString());
        }
        return "";
    }

    public static String post(String url, Map<String, String> postParameters) {
        LogUtil.e("HttpUtil", url);
        try {
            FormBody.Builder builder = new FormBody.Builder();
            if  (postParameters == null) {
                postParameters = new HashMap<>();
            }
            Set<String> set = postParameters.keySet();
            for (String key : set) {
                builder.add(key,postParameters
                        .get(key));
            }
            RequestBody formBody = builder.build();
            Request request = new Request.Builder()
                    .url(url)
                    .post(formBody)
                    .build();
            Response response = getOkHttpClient().newCall(request).execute();
            String body = response.body().string();
            if (response.isSuccessful()) {
                return body;
            }
        } catch (Exception e) {
            LogUtil.e("HttpUtil", e.toString());
        }
        return "";

    }

    public static String put(String url, Map<String, String> postParameters) {
        LogUtil.e("HttpUtil", url);
        try {
            FormBody.Builder builder = new FormBody.Builder();
            if  (postParameters == null) {
                postParameters = new HashMap<>();
            }
            Set<String> set = postParameters.keySet();
            for (String key : set) {
                builder.add(key,postParameters
                        .get(key));
            }
            RequestBody formBody = builder.build();
            Request request = new Request.Builder()
                    .url(url)
                    .put(formBody)
                    .build();

            Response response = getOkHttpClient().newCall(request).execute();

            String body = response.body().string();
            if (response.isSuccessful()) {
                return body;
            }
        } catch (Exception e) {
            LogUtil.e("HttpUtil", e.toString());
        }
        return "";

    }

    public static String delete(String url, Map<String, String> postParameters) {
        LogUtil.e("HttpUtil", url);
        try {
            FormBody.Builder builder = new FormBody.Builder();
            if  (postParameters == null) {
                postParameters = new HashMap<>();
            }
            Set<String> set = postParameters.keySet();
            for (String key : set) {
                builder.add(key,postParameters
                        .get(key));
            }

            RequestBody formBody = builder.build();
            Request request = new Request.Builder()
                    .url(url)
                    .method("DELETE", formBody)
                    .build();
            Response response = getOkHttpClient().newCall(request).execute();

            String body = response.body().string();
            if (response.isSuccessful()) {
                return body;
            }
        } catch (Exception e) {
            LogUtil.e("HttpUtil", e.toString());
        }
        return "";

    }

    public static String upload(String url, ArrayList<String> files) {
        LogUtil.i("HttpUtil","upload files url is \n" + " ::: "+files);
        try {

            if (files == null || files.size() == 0) {
                return "";
            }
            MultipartBody.Builder builder = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM);
            FileNameMap fileNameMap = URLConnection.getFileNameMap();
            RequestBody fileBody;
            Uri uri;
            for (int i = 0; i < files.size(); i++)
            {
                uri = Uri.parse(files.get(i));
                File file = new File(files.get(i));
                String fileName = file.getName();
                String contentTypeFor = fileNameMap.getContentTypeFor(fileName);
                if (contentTypeFor == null)
                {
                    contentTypeFor = "multipart/form-data";
                }
                fileBody = RequestBody.create(MediaType.parse(contentTypeFor), file);
                builder.addPart(Headers.of("Content-Disposition",
                        "form-data; name=\"" + "upload" + "\"; filename=\"" + uri.getLastPathSegment() + "\""),
                        fileBody);
            }
            RequestBody requestBody = builder.build();
            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();
            Response response = getOkHttpClient().newCall(request).execute();
            String body = response.body().string();
            if (response.isSuccessful()) {
                return body;
            }
        } catch (Exception e) {
            LogUtil.e("HttpUtil", e.toString());
        }
        return "";

    }

    public static boolean download(String url, String file) {
        LogUtil.i("HttpUtil","getFile url to path\n" + " ::: "+file);
        try {
            int len;
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = getOkHttpClient().newCall(request).execute();
            if (!response.isSuccessful()) {
                return false;
            }
            InputStream inStream = response.body().byteStream();
            FileOutputStream fos = new FileOutputStream(file);
            byte[] buffer = new byte[1204];
            while ((len = inStream.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            return true;
        } catch (Exception e) {
            LogUtil.e("HttpUtil", e.toString());
        }
        return false;
    }

    private static OkHttpClient getOkHttpClient() {
        if (sOkHttpClient == null) {
            synchronized (HttpUtil.class) {
                if (sOkHttpClient == null) {
                    OkHttpClient.Builder builder = new OkHttpClient.Builder();
                    if (LogUtil.isDebug) {
                        builder.networkInterceptors().add(new StethoInterceptor());
                    }
                    builder.connectTimeout(10, TimeUnit.SECONDS);
                    builder.writeTimeout(10, TimeUnit.SECONDS);
                    builder.readTimeout(30, TimeUnit.SECONDS);
                    sOkHttpClient = builder.build();
                }
            }
        }
        return sOkHttpClient;
    }

}
