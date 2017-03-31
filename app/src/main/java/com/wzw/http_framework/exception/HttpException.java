package com.wzw.http_framework.exception;

/**
 * 全局异常类
 * Created by Henry on 2017/3/26.
 */

public class HttpException extends Exception {
    public int mStatusCode;
    public String mResponseMessage;
    public enum ErrorType{
        CANCEL,
        TIMEOUT,
        SERVER,
        JSON,
        IO,
    }
    public ErrorType mErrorType;
    public HttpException(int code, String message){
        this.mErrorType = ErrorType.SERVER;
        this.mStatusCode = code;
        this.mResponseMessage = message;
    }
    public HttpException(ErrorType type, String message){
        this.mErrorType = type;
        this.mResponseMessage = message;
    }
    public HttpException(ErrorType type, int code, String message){
        this.mErrorType = type;
        this.mStatusCode = code;
        this.mResponseMessage = message;
    }
}
