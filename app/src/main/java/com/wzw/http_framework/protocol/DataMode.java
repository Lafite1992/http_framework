package com.wzw.http_framework.protocol;

/**
 * 数据读取模式协议
 * Created by Henry on 2017/3/24.
 */

public enum DataMode {
    /**
     直接从网络取数据不需要本地存储
     DATA_FROM_NET_NO_CACHE
     直接从网络取数据并且需要本地存储
     DATA_FROM_NET_NEED_CACHE
     直接从本地存储取数据
     DATA_FROM_CACHE
     先从本地存储取数据显示出来 再去网络取数据更新界面并本地存储
     DATA_FROM_NET_UPDATE_CACHE
     */
    DATA_FROM_NET_NO_CACHE,
    DATA_FROM_NET_NEED_CACHE,
    DATA_FROM_CACHE,
    DATA_FROM_NET_UPDATE_CACHE
}
