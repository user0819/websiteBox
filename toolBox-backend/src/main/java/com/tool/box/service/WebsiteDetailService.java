package com.tool.box.service;

import com.tool.box.mapper.WebsiteDetailMapper;
import com.tool.box.model.WebsiteDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebsiteDetailService {
    @Autowired
    private WebsiteDetailMapper websiteDetailMapper;

    public List<WebsiteDetail> findByCategoryId(Integer categoryId) {
        return websiteDetailMapper.findByCategoryId(categoryId);
    }

    public List<WebsiteDetail> findAll() {
        return websiteDetailMapper.findAll();
    }

    public int updateById(WebsiteDetail websiteDetail) {
        return websiteDetailMapper.updateDetail(websiteDetail);

    }

    public int deleteById(Integer id) {
        return websiteDetailMapper.deleteById(id);
    }

    public int saveDetail(WebsiteDetail websiteDetail) {
        return websiteDetailMapper.saveDetail(websiteDetail);
    }

    public int updateSort(List<WebsiteDetail> websiteDetailList) {
        int count = 0;
        for (WebsiteDetail websiteDetail : websiteDetailList) {
            count +=websiteDetailMapper.updateDetail(websiteDetail);
        }
        return count;
    }

    public List<WebsiteDetail> findByQueryParam(Integer categoryId, String name, String url) {
        return websiteDetailMapper.findByQueryParam(categoryId, name, url);
    }
}