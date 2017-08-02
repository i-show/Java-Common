package com.ishow.ssm.controller;

import com.ishow.ssm.model.BaseVersion;
import com.ishow.ssm.model.Result;
import com.ishow.ssm.model.VerifyCode;
import com.ishow.ssm.model.Version;
import com.ishow.ssm.model.exchange.Exchanged;
import com.ishow.ssm.service.VerifyCodeService;
import com.ishow.ssm.service.VersionService;
import com.ishow.ssm.util.manager.RESTManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping("/version")
@Api(tags = "Version", description = "版本")
public class VersionController {
    @Resource
    private VersionService service;

    @ResponseBody
    @RequestMapping(value = "/getVersion", method = RequestMethod.POST, consumes = "application/json")
    @ApiOperation(tags = "Version", value = "获取版本信息", httpMethod = "POST", consumes = "application/json")
    public Result getVersion(@RequestBody BaseVersion version) {
        try {
            Version serviceVersion = service.getVersion(version.getDeviceType());
            // 如果请求的版本号大于服务器配置的 那么返回已经是最新编版本
            if (version.getVersionCode() >= serviceVersion.getVersionCode()) {
                serviceVersion.setStatus(Version.Key.STATUS_ALREAY_NEWEST);
            }
            return RESTManager.sendSuccessResponse(serviceVersion);
        } catch (Exception e) {
            return RESTManager.sendFailedResponse(e.getMessage());
        }
    }

}
