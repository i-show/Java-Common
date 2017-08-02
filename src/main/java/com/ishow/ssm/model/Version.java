package com.ishow.ssm.model;

/**
 * Created by yuhaiyang on 2017/7/13.
 * 版本更新
 */
public class Version extends BaseVersion {
    private int status;
    private String address;
    private String description;
    private String updateTime;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
