package com.tool.box.controller;

import com.tool.box.dto.HttpResponse;
import com.tool.box.dto.LoginParams;
import com.tool.box.dto.LoginResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @RequestMapping("/login")
    public HttpResponse<LoginResult> login(@RequestBody LoginParams loginParams) {
        return HttpResponse.success(new LoginResult("myTestToken"));
    }
}
