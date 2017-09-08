package com.zerods.mall.manager.controller;

import com.Response.result.ResponseResult;
import com.zerods.mall.common.pojo.EasyUIDataGridResult;
import com.zerods.mall.content.service.ContentService;
import com.zerods.mall.pojo.TbContent;
import com.zerods.mall.pojo.TbItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zerods
 * @version 1.0 08/09/2017
 */
@Controller
public class ContentController {
    @Autowired
    private ContentService contentService;

    @RequestMapping("/content/query/list")
    @ResponseBody
    public EasyUIDataGridResult<TbContent> getContentList(Integer page, Integer rows) {
        EasyUIDataGridResult<TbContent> result = contentService.getContentList(page, rows);
        return result;
    }

    @RequestMapping(value = "/content/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult addContent(TbContent content) {
        ResponseResult result = contentService.addContent(content);
        return result;
    }
}
