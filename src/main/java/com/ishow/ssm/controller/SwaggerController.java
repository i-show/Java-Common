package com.ishow.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 仅用来跳转的
 */
@Controller
@ApiIgnore
public class SwaggerController {

    @RequestMapping("/swagger")
    public void swagger(HttpServletResponse resp) throws Exception {
        handleRequest(resp);
    }

    @RequestMapping("/api")
    public void api(HttpServletResponse resp) throws Exception {
        handleRequest(resp);
    }

    private void handleRequest(HttpServletResponse resp) throws Exception {
        resp.sendRedirect("swagger-ui.html");
    }
}
