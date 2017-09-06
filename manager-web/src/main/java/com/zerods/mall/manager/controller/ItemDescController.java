package com.zerods.mall.manager.controller;

import com.zerods.mall.pojo.TbItemDesc;
import com.zerods.mall.service.ItemDescService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author zerods
 * @version 1.0 06/09/2017
 */
@Controller
@RequestMapping("item/desc")
public class ItemDescController {
    private static final Logger LOG = LoggerFactory.getLogger(ItemDescController.class);
    
    @Autowired
    private ItemDescService itemDescService;
    /**
     * 根据商品id查询商品描述信息
     */
    @RequestMapping(value = "{itemId}",method = RequestMethod.GET)
    public ResponseEntity<TbItemDesc> queryItemDescByItemId(
            @PathVariable("itemId") Long itemId) {
        if (LOG.isInfoEnabled()){
            LOG.info("商品描述信息查询 id = {}",itemId);
        }
        try {
            TbItemDesc itemDesc = this.itemDescService.queryItemDescByItemId(itemId);
            if (itemDesc == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(itemDesc);
        } catch (Exception e) {
            LOG.error("商品描述查询失败id = {}",itemId,e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
