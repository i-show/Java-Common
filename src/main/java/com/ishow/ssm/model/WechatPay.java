package com.ishow.ssm.model;

import java.util.Calendar;
import java.util.Random;
import java.util.TimeZone;
import java.util.UUID;

/**
 * Created by yuhaiyang on 2017/7/20.
 * 微信支付
 */
public class WechatPay {
    public String apid;
    public String partnerid;
    public String prepayid;
    public String extPackage = "Sign=WXPay";
    public String noncestr;
    public String timestamp;
    public String sign;




    /**
     * 生成时间戳
     * 注意必须要是北京时间
     * <p>
     * https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=4_3
     */
    public static String genTimestamp() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Shanghai"));
        return String.valueOf(calendar.getTimeInMillis() / 1000);
    }
}
