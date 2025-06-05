package com.tool.box.controller;

import com.tool.box.dto.HttpResponse;
import com.tool.box.dto.UserInfo;
import com.tool.box.util.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @RequestMapping("/info")
    public HttpResponse<UserInfo> info(HttpServletRequest request, HttpServletResponse response) {
        // 从 Header 中获取 Token
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // 去掉 "Bearer " 前缀
        }

        if (token == null || token.isEmpty() || !JwtUtils.validateToken(token)) {
            // 设置 HTTP 状态码为 401
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return HttpResponse.fail(HttpStatus.UNAUTHORIZED.value(), "Token 无效");
        }

        // 解析用户名
        String username = JwtUtils.parseToken(token);
        if (username == null) {
            // 设置 HTTP 状态码为 401
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return HttpResponse.fail(HttpStatus.UNAUTHORIZED.value(), "Token 无效");
        }


        // 构造 UserInfo 返回给前端
        UserInfo userInfo = new UserInfo(new ArrayList<>(), username);
        return HttpResponse.success(userInfo);
    }
}
