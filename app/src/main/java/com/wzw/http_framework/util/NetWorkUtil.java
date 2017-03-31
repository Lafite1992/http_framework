package com.wzw.http_framework.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 检查网络状态工具类
 * Created by Henry on 2017/3/31.
 */

public class NetWorkUtil {
    public static boolean isConnect(Context context){
        ConnectivityManager connectivityManager=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo=connectivityManager.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

    public static boolean isWifiConnect(Context context){
        ConnectivityManager connectivityManager=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return wifiInfo != null && wifiInfo.isConnected();
    }

    public static boolean isMobileConnect(Context context){
        ConnectivityManager connectivityManager=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobileNetInfo=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return mobileNetInfo != null && mobileNetInfo.isConnected();
    }
}
