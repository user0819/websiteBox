package com.tool.box.mapper;

import com.tool.box.model.WebsiteDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WebsiteDetailMapper {
    List<WebsiteDetail> findByCategoryId(Integer categoryId);

    List<WebsiteDetail> findAll();

    int updateDetail(WebsiteDetail websiteDetail);

    int deleteById(Integer id);

    int saveDetail(WebsiteDetail websiteDetail);


    List<WebsiteDetail> findByQueryParam(@Param("categoryId") Integer categoryId,
                                         @Param("name") String name,
                                         @Param("url") String url);
}