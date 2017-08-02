package com.ishow.ssm.controller;

import com.ishow.ssm.model.Result;
import com.ishow.ssm.model.User;
import com.ishow.ssm.model.VerifyCode;
import com.ishow.ssm.model.exchange.Exchanged;
import com.ishow.ssm.service.UserService;
import com.ishow.ssm.service.VerifyCodeService;
import com.ishow.ssm.util.manager.RESTManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/verifyCode")
@Api(tags = "VerifyCode", description = "验证码")
public class VerifyCodeController {
    @Resource
    private VerifyCodeService service;

    @ResponseBody
    @RequestMapping("/getCode")
    @ApiOperation(tags = "VerifyCode", value = "获取验证码", httpMethod = "GET")
    public Result getCode(@RequestParam(name = "phone") String phone) {
        try {
            VerifyCode code = service.getCode(phone);
            return RESTManager.sendSuccessResponse(code);
        } catch (Exception e) {
            return RESTManager.sendFailedResponse(e.getMessage());
        }
    }


    @ResponseBody
    @RequestMapping("/insertCode")
    @ApiOperation(tags = "VerifyCode", value = "存储验证码", httpMethod = "POST")
    public Result insertCode(@RequestParam(required = false, name = "userId") String userId, @RequestParam(name = "phone") String phone, @RequestParam(name = "code") String code, @RequestParam(name = "time") long time) {
        long result = service.insertCode(userId, phone, code, time);
        if (result > 0) {
            return RESTManager.sendSuccessResponse(null);
        } else {
            return RESTManager.sendFailedResponse("未知错误");
        }
    }
}
