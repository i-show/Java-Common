package com.ishow.ssm.controller;

import com.ishow.ssm.model.Result;
import com.ishow.ssm.model.User;
import com.ishow.ssm.model.exchange.Exchanged;
import com.ishow.ssm.service.UserService;
import com.ishow.ssm.util.manager.RESTManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/User")
@Api(tags = "User", description = "用户相关操作")
public class UserController {

    @Resource
    private UserService userService;

    @ResponseBody
    @RequestMapping("/list")
    @ApiOperation(tags = "User", value = "用户列表", httpMethod = "GET")
    public Result showUser() {
        List<User> userList = userService.getAllUser();
        return RESTManager.sendSuccessResponse(userList);
    }


    @ResponseBody
    @RequestMapping("/getUserById")
    @ApiOperation(tags = "User", value = "获取用户信息", httpMethod = "GET")
    @ApiParam(required = true, name = "id", value = "用户ID")
    public Result getUserById(@RequestParam(name = "id") long id) {
        User user = userService.getUserById(id);
        return RESTManager.sendSuccessResponse(user);
    }

    @ResponseBody
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ApiOperation(tags = "User", value = "获取用户信息", httpMethod = "POST")
    @ApiParam(required = true, name = "id", value = "用户ID")
    public Result getById(@RequestParam(value = "id") long id, @RequestHeader(value = "token", required = false) String token) {
        User user = userService.getUserById(id);
        user.setToken(token);
        return RESTManager.sendSuccessResponse(user);
    }


    @ResponseBody
    @RequestMapping(value = "/getUser", method = RequestMethod.POST, consumes = "application/json")
    @ApiOperation(tags = "User", value = "获取用户信息", httpMethod = "POST", consumes = "application/json")
    @ApiParam(required = true)
    public Result getUser(@RequestBody Exchanged exchanged) {
        User user = userService.getUserById(exchanged.id);
        return RESTManager.sendSuccessResponse(user);
    }


}
