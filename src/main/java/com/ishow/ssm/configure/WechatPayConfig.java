package com.ishow.ssm.configure;

/**
 * Created by yuhaiyang on 2017/7/20.
 * 微信支付的配置文件
 */
public class WechatPayConfig {
    /**
     * 微信开发平台应用ID
     */
    public static final String APP_ID = "wx15bdb0998ef73a3b";
    /**
     * 应用对应的凭证
     */
    public static final String APP_SECRET = "5ea42c39276aa7c5e8aaf0eff798e823";
    /**
     * 应用对应的密钥
     */
    public static final String APP_KEY = "5ea42c39276aa7c5e8aaf0eff7201707";
    /**
     * 微信支付商户号
     */
    public static final String MCH_ID = "1484581962";
    /**
     * 商品描述
     */
    public static final String BODY = "QQ游戏-账户充值";
    /**
     * 商户号对应的密钥
     */
    public static final String PARTNER_KEY= "123466";

    /**
     * 商户id
     */
    public static final String PARTNER_ID = "14698sdfs402dsfdew402";
    /**
     * 常量固定值
     */
    public static final String GRANT_TYPE = "client_credential";
    /**
     * 获取预支付id的接口url
     */
    public static String GATEURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    /**
     * 微信服务器回调通知url
     */
    public static String NOTIFY_URL = "http://www.ishow.club/JavaCommon/pay/app/tenpay/notify";

}
