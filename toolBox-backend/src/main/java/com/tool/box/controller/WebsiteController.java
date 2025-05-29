package com.tool.box.controller;

import com.tool.box.dto.HttpResponse;
import com.tool.box.model.WebsiteDetail;
import com.tool.box.service.WebsiteDetailService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/website")
public class WebsiteController {
    @Resource
    private WebsiteDetailService websiteDetailService;


    @GetMapping
    public HttpResponse<List<WebsiteDetail>> query(@RequestParam(required = false) Integer categoryId,
                                                   @RequestParam(required = false) String name,
                                                   @RequestParam(required = false) String url) {
        return HttpResponse.success(websiteDetailService.findByQueryParam(categoryId,  name, url));
    }

    @PostMapping
    public HttpResponse<Boolean> add(@RequestBody WebsiteDetail websiteDetail) {
        websiteDetailService.saveDetail(websiteDetail);
        return HttpResponse.success(true);
    }

    @PutMapping
    public HttpResponse<Boolean> update(@RequestBody WebsiteDetail websiteDetail) {
        websiteDetailService.updateById(websiteDetail);
        return HttpResponse.success(true);

    }

    @DeleteMapping("/{id}")
    public HttpResponse<Boolean> delete(@PathVariable int id) {
        websiteDetailService.deleteById(id);
        return HttpResponse.success(true);
    }

}