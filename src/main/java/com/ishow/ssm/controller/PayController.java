package com.ishow.ssm.controller;

import com.ishow.ssm.controller.wechatpay.WechatPayController;
import com.ishow.ssm.model.Order;
import com.ishow.ssm.model.Result;
import com.ishow.ssm.util.HttpUtils;
import com.ishow.ssm.util.XMLUtil;
import com.ishow.ssm.util.manager.RESTManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/pay")
@Api(tags = "Pay", description = "支付")
public class PayController {
    /**
     * Logger for this class
     */
    private static Logger logger = Logger.getLogger(PayController.class);

    @Autowired
    private HttpServletRequest request;

    @ResponseBody
    @RequestMapping("/wechatPay")
    @ApiOperation(tags = "Pay", value = "微信支付", httpMethod = "POST")
    public Result wechatPay(@RequestParam(required = false, name = "orderId") String orderId) {
        String ip = null;
        try {
            ip = HttpUtils.getIpAddress(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("wechatPay ip = " + ip);
        Order order = new Order(orderId);
        WechatPayController wechatPay = new WechatPayController(order, "222.92.90.18");

        try {
            return RESTManager.sendSuccessResponse(wechatPay.getPayInfo());
        } catch (Exception e) {
            logger.info("wechatPay e = " + e);
            return RESTManager.sendFailedResponse(e.toString());
        }
    }


    /**
     * 接收微信支付成功通知
     */
    @ApiIgnore
    @RequestMapping(value = "/app/tenpay/notify")
    public void getnotify(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        System.out.println("微信支付回调");
    }
}
