package com.tool.box.service;

import com.tool.box.mapper.WebsiteCategoryMapper;
import com.tool.box.model.WebsiteCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebsiteCategoryService {
    @Autowired
    private WebsiteCategoryMapper websiteCategoryMapper;

    public List<WebsiteCategory> findAll() {
        return websiteCategoryMapper.findAll();
    }

    public void insertWebsiteCategory(WebsiteCategory category) {
        websiteCategoryMapper.insertWebsiteCategory(category);
    }

    public void updateWebsiteCategory(WebsiteCategory category) {
        websiteCategoryMapper.updateWebsiteCategory(category);
    }

    public void deleteById(int id) {
        websiteCategoryMapper.deleteById(id);
    }
}