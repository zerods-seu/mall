package com.zerods.mall.controller;

import com.zerods.mall.pojo.TbItem;
import com.zerods.mall.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
        TbItem tbItem = itemService.getItemById(itemId);
        return tbItem;
    }
}
