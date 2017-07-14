package com.ishow.ssm.model;

import java.util.Date;

/**
 * Created by yuhaiyang on 2017/7/13.
 * 验证码
 */
public class VerifyCode {
    private String phone;
    private String code;
    private String userId;
    private long time;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
