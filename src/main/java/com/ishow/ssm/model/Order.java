package com.ishow.ssm.model;

/**
 * Created by yuhaiyang on 2017/7/20.
 */
public class Order {
    public String id;
    public String totalFee;

    public Order(String orderid) {
        id = orderid;
        totalFee = "1";
    }

    public String getId() {
        return id;
    }

    public String getTotalFee() {
        return totalFee;
    }
}
