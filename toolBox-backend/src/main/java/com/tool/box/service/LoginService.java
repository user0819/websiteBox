package com.tool.box.service;

import com.tool.box.dto.HttpResponse;
import com.tool.box.dto.LoginParams;
import com.tool.box.dto.LoginResult;
import com.tool.box.mapper.UserMapper;
import com.tool.box.model.User;
import com.tool.box.util.JwtUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class LoginService {
    @Resource
    private UserMapper userMapper;

    // 模拟 Token 缓存
    private static final Map<String, String> tokenCache = new ConcurrentHashMap<>();
    private static final int MAX_TOKEN_COUNT = 100;

    public HttpResponse<LoginResult> login(LoginParams loginParams) {
        String username = loginParams.getUsername();
        String password = loginParams.getPassword();

        // 查询用户
        User user = userMapper.selectByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            return HttpResponse.fail("用户名或密码错误");
        }

        // 生成 Token
        String token = JwtUtils.generateToken(user.getUsername());

        // 清理过期 Token 并保存新 Token
        if (tokenCache.size() >= MAX_TOKEN_COUNT) {
            tokenCache.clear(); // 实际可按时间清理
        }
        tokenCache.put(token, username);

        return HttpResponse.success(new LoginResult(token));
    }
}
