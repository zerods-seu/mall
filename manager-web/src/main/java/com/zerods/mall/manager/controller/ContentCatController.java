package com.zerods.mall.manager.controller;

import com.Response.result.ResponseResult;
import com.zerods.mall.common.pojo.EasyUITeeNode;
import com.zerods.mall.content.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author zerods
 * @version 1.0 07/09/2017
 */
@Controller
public class ContentCatController {

    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping("content/category/list")
    @ResponseBody
    public List<EasyUITeeNode> getContentCatList(
            @RequestParam(name = "id", defaultValue = "0") Long parentId) {
        return contentCategoryService.getContentCatList(parentId);
    }

    @RequestMapping(value = "content/category/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult addContentCatagory(Long parentId, String name) {
        return contentCategoryService.addContentCatagory(parentId, name);
    }
}
