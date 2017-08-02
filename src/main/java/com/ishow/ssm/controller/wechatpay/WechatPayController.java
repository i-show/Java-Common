package com.ishow.ssm.controller.wechatpay;

import com.ishow.ssm.configure.WechatPayConfig;
import com.ishow.ssm.controller.PayController;
import com.ishow.ssm.model.Order;
import com.ishow.ssm.model.WechatPay;
import com.ishow.ssm.util.EncryptUtils;
import com.ishow.ssm.util.StringUtils;
import com.ishow.ssm.util.XMLUtil;
import okhttp3.*;
import org.apache.log4j.Logger;
import org.jdom2.JDOMException;

import java.io.IOException;
import java.util.*;

/**
 * Created by yuhaiyang on 2017/7/20.
 * 微信支付
 */
public class WechatPayController {
    /**
     * Logger for this class
     */
    private static Logger logger = Logger.getLogger(WechatPayController.class);

    private SortedMap<String, String> mPerPayParames = new TreeMap<String, String>();
    private SortedMap<String, String> mFinialParames = new TreeMap<String, String>();

    public WechatPayController(Order order, String ip) {
        // APP的ID
        mPerPayParames.put("appid", WechatPayConfig.APP_ID);
        // 商户ID
        mPerPayParames.put("mch_id", WechatPayConfig.MCH_ID);
        // 随机字符串
        mPerPayParames.put("nonce_str", StringUtils.genRandomString());
        // 订单描述
        mPerPayParames.put("body", "青青乐-商品订单");
        // 订单号
        mPerPayParames.put("out_trade_no", order.getId());
        // 订单金额
        mPerPayParames.put("total_fee", order.getTotalFee());
        // 终端IP地址
        mPerPayParames.put("spbill_create_ip", ip);
        // 通知地址
        mPerPayParames.put("notify_url", WechatPayConfig.NOTIFY_URL);
        // 交易类型
        mPerPayParames.put("trade_type", "APP");
        // 签名
        mPerPayParames.put("sign", createSign(mPerPayParames));

        logger.info("mPerPayParames = " + mPerPayParames);
    }

    public WechatPay getPayInfo() throws Exception {
        // 只有json样式或者只有一个File的
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8");
        String bodyString = getRequestXml(mPerPayParames);
        logger.info("bodyString = " + bodyString);
        RequestBody body = RequestBody.create(mediaType, bodyString);

        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.url(WechatPayConfig.GATEURL);
        requestBuilder.post(body);
        Request request = requestBuilder.build();

        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();
        //noinspection ConstantConditions
        String reusult = response.body().string();
        response.close();
        logger.info("result = " + reusult);
        String perpayId = getPerPayId(reusult);

        mFinialParames.put("appid", WechatPayConfig.APP_ID);
        mFinialParames.put("partnerid", WechatPayConfig.MCH_ID);
        mFinialParames.put("prepayid", perpayId);
        mFinialParames.put("package", "Sign=WXPay");
        mFinialParames.put("noncestr", mPerPayParames.get("nonce_str"));
        mFinialParames.put("timestamp", WechatPay.genTimestamp());

        WechatPay pay = new WechatPay();
        pay.apid = WechatPayConfig.APP_ID;
        pay.partnerid = WechatPayConfig.MCH_ID;
        pay.prepayid = perpayId;
        pay.noncestr = mFinialParames.get("noncestr");
        pay.timestamp = mFinialParames.get("timestamp");
        pay.sign = createSign(mFinialParames);
        return pay;
    }

    /**
     * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
     */
    private String createSign(SortedMap<String, String> map) {
        StringBuilder builder = new StringBuilder();
        Set set = map.entrySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            if (!StringUtils.isEmpty(value) && !StringUtils.equals(key, "sign") && !StringUtils.equals(key, "key")) {
                builder.append(key);
                builder.append("=");
                builder.append(value);
                builder.append("&");
            }
        }

        builder.append("key=");
        builder.append(WechatPayConfig.APP_KEY);
        String result = builder.toString();
        logger.info("createSign = " + result);
        logger.info("createSign1 = " + EncryptUtils.md5(result));

        return EncryptUtils.md5(result).toUpperCase();
    }


    private String getRequestXml(SortedMap<String, String> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();

            if (!"sign".equalsIgnoreCase(k)) {
                sb.append("<" + k + ">" + v + "</" + k + ">");
            }
            if ("sign".equalsIgnoreCase(k)) {

            } else if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k)) {
                sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
            } else {
                sb.append("<" + k + ">" + v + "</" + k + ">");
            }
        }
        sb.append("<" + "sign" + ">" + "<![CDATA[" + parameters.get("sign") + "]]></" + "sign" + ">");
        sb.append("</xml>");
        return sb.toString();
    }

    private String getPerPayId(String result) throws JDOMException, IOException {
        Map map = XMLUtil.doXMLParse(result);
        String return_code = (String) map.get("return_code");
        if (return_code.contains("SUCCESS")) {
            return (String) map.get("prepay_id");//获取到prepay_id
        } else {
            return null;
        }
    }
}
