package com.tool.box.controller;

import com.tool.box.dto.HttpResponse;
import com.tool.box.model.WebsiteDetail;
import com.tool.box.service.WebsiteDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/details")
public class WebsiteDetailController {
    @Autowired
    private WebsiteDetailService websiteDetailService;

    @GetMapping("/category/{categoryId}")
    public HttpResponse<List<WebsiteDetail>> getDetailsByCategoryId(@PathVariable Integer categoryId) {
        return HttpResponse.success(websiteDetailService.findByCategoryId(categoryId));
    }

    @PutMapping("/{id}")
    public int updateDetail(@RequestBody WebsiteDetail websiteDetail) {
        return websiteDetailService.updateById(websiteDetail);

    }

    @DeleteMapping("/{id}")
    public int deleteDetail(@PathVariable Integer id) {
        return websiteDetailService.deleteById(id);
    }

    @PostMapping("/")
    public int saveDetail(@RequestBody WebsiteDetail websiteDetail) {
        return websiteDetailService.saveDetail(websiteDetail);
    }

    @PutMapping("/sort")
    public int sort(@RequestBody List<WebsiteDetail> websiteDetailList) {
        return websiteDetailService.updateSort(websiteDetailList);
    }
}