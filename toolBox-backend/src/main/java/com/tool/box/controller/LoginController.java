package com.tool.box.controller;

import com.tool.box.dto.HttpResponse;
import com.tool.box.dto.LoginParams;
import com.tool.box.dto.LoginResult;
import com.tool.box.service.LoginService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Resource
    private LoginService loginService;

    @RequestMapping("/login")
    public HttpResponse<LoginResult> login(@RequestBody LoginParams loginParams) {
        return loginService.login(loginParams);
    }
}
