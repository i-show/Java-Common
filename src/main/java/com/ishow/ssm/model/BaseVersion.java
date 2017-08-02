package com.ishow.ssm.model;

/**
 * Created by yuhaiyang on 2017/7/13.
 * 版本更新
 */
public class BaseVersion {
    private int deviceType;
    private int versionCode;
    private String versionName;

    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public static final class Key {
        /**
         * 已经是最新版本
         */
        public static final int STATUS_ALREAY_NEWEST = -1;
        /**
         * 有最新版本不需要强制升级
         */
        public static final int STATUS_CAN_UPDATE = 0;
        /**
         * 强制升级
         */
        public static final int STATUS_FORCE_UPDATE = 1;
    }
}
