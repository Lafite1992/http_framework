# http_framework
具体使用方式详见项目中的MainActivity，里面有示例代码。
网络请求框架功能：
HttpRequest + JsonParse,需要的实体Bean在主线程中返回;
高并发采用线程池管理;
支持常见4种请求方式：get post put delete
支持多文件上传，支持文件下载
支持本地缓存，并且有4种DataMode模式，例如：DATA_FROM_NET_UPDATE_CACHE ： 先从本地存储取数据显示出来 再去网络取数据更新界面并本地存储
在网络请求失败的情况下，根据ErrorType可以方便的定位问题所在，易于debug
支持取消网络请求,绑定activity生命周期，在activity被销毁时取消相关的Request

更多功能待后续扩展，此框架会不断完善   
