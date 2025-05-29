package com.tool.box.controller;

import com.tool.box.dto.HttpResponse;
import com.tool.box.dto.UserInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @RequestMapping("/info")
    public HttpResponse<UserInfo> info() {
        UserInfo admin = new UserInfo(new ArrayList<>(), "admin");
        return HttpResponse.success(admin);
    }
}
