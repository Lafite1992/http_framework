package com.wzw.http_framework;

import java.io.Serializable;

/**
 *
 * Created by Henry on 2017/3/31.
 */

public class CommonBean implements Serializable {


    /**
     * code : 0
     * message : 成功
     * result : {"liveId":31074,"circleId":"c1efa3159915b02d4-7ffe","userId":"34d1bc1545c152a1a-7ea3","liveAuth":1,"authData":"","viewAuth":true,"hasPay":0,"inFreeSeat":1}
     */

    private int code;
    private String message;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * liveId : 31074
         * circleId : c1efa3159915b02d4-7ffe
         * userId : 34d1bc1545c152a1a-7ea3
         * liveAuth : 1
         * authData :
         * viewAuth : true
         * hasPay : 0
         * inFreeSeat : 1
         */

        private int liveId;
        private String circleId;
        private String userId;
        private int liveAuth;
        private String authData;
        private boolean viewAuth;
        private int hasPay;
        private int inFreeSeat;

        public int getLiveId() {
            return liveId;
        }

        public void setLiveId(int liveId) {
            this.liveId = liveId;
        }

        public String getCircleId() {
            return circleId;
        }

        public void setCircleId(String circleId) {
            this.circleId = circleId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public int getLiveAuth() {
            return liveAuth;
        }

        public void setLiveAuth(int liveAuth) {
            this.liveAuth = liveAuth;
        }

        public String getAuthData() {
            return authData;
        }

        public void setAuthData(String authData) {
            this.authData = authData;
        }

        public boolean isViewAuth() {
            return viewAuth;
        }

        public void setViewAuth(boolean viewAuth) {
            this.viewAuth = viewAuth;
        }

        public int getHasPay() {
            return hasPay;
        }

        public void setHasPay(int hasPay) {
            this.hasPay = hasPay;
        }

        public int getInFreeSeat() {
            return inFreeSeat;
        }

        public void setInFreeSeat(int inFreeSeat) {
            this.inFreeSeat = inFreeSeat;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "liveId=" + liveId +
                    ", circleId='" + circleId + '\'' +
                    ", userId='" + userId + '\'' +
                    ", liveAuth=" + liveAuth +
                    ", authData='" + authData + '\'' +
                    ", viewAuth=" + viewAuth +
                    ", hasPay=" + hasPay +
                    ", inFreeSeat=" + inFreeSeat +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CommonBean{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }
}
