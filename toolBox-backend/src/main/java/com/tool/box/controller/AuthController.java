package com.tool.box.controller;

import com.tool.box.dto.HttpResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @RequestMapping("/codes")
    public HttpResponse<List<String>> codes() {
        return HttpResponse.success(new ArrayList<>());
    }
}
