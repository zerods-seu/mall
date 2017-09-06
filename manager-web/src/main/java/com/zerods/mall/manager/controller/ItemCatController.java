package com.zerods.mall.manager.controller;

import com.zerods.mall.common.pojo.EasyUITeeNode;
import com.zerods.mall.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author zerods
 * @version 1.0 03/09/2017
 */
@Controller
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("/item/cat/list")
    @ResponseBody
    public List<EasyUITeeNode> getItemCatList(
            @RequestParam(name = "id", defaultValue = "0")Long parentId) {
        List<EasyUITeeNode> treeNodes = itemCatService.getItemCatList(parentId);
        return treeNodes;
    }
}
