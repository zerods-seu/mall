package com.zerods.mall.manager.controller;

import com.Response.result.ResponseResult;
import com.zerods.mall.common.pojo.EasyUIDataGridResult;
import com.zerods.mall.pojo.TbItem;
import com.zerods.mall.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zerods
 * @version 1.0 01/09/2017
 */
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable long itemId) {
        return itemService.getItemById(itemId);
    }

    @RequestMapping("/item/list")
    @ResponseBody
    public EasyUIDataGridResult<TbItem> getItemList(Integer page, Integer rows) {
        EasyUIDataGridResult<TbItem> result = itemService.getItemList(page, rows);
        return result;
    }

    @RequestMapping(value = "/item/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult addItem(TbItem item, String desc) {
        return itemService.addItem(item, desc);
    }
}
