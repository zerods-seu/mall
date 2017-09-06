package com.zerods.mall.service.impl;

import com.zerods.mall.mapper.TbItemDescMapper;
import com.zerods.mall.pojo.TbItemDesc;
import com.zerods.mall.service.ItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zerods
 * @version 1.0 06/09/2017
 */
@Service
public class ItemDescServiceImpl implements ItemDescService {

    @Autowired
    private TbItemDescMapper itemDescMapper;

    @Override
    public TbItemDesc queryItemDescByItemId(Long itemId) {
        return itemDescMapper.selectByPrimaryKey(itemId);
    }
}
