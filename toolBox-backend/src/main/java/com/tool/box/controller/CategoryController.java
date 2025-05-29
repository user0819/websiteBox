package com.tool.box.controller;

import com.tool.box.dto.HttpResponse;
import com.tool.box.model.WebsiteCategory;
import com.tool.box.service.WebsiteCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private WebsiteCategoryService websiteCategoryService;

    @GetMapping
    public HttpResponse<List<WebsiteCategory>> query() {
        return HttpResponse.success(websiteCategoryService.findAll());
    }

    @PostMapping
    public HttpResponse<Boolean> add(@RequestBody WebsiteCategory category) {
        websiteCategoryService.insertWebsiteCategory(category);
        return HttpResponse.success(true);
    }

    @PutMapping
    public HttpResponse<Boolean> update(@RequestBody WebsiteCategory category) {
        websiteCategoryService.updateWebsiteCategory(category);
        return HttpResponse.success(true);

    }

    @DeleteMapping("/{id}")
    public HttpResponse<Boolean> delete(@PathVariable int id) {
        websiteCategoryService.deleteById(id);
        return HttpResponse.success(true);
    }

}