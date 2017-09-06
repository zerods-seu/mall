package com.zerods.mall.service;

import com.zerods.mall.pojo.TbItemDesc;

/**
 * @author zerods
 * @version 1.0 06/09/2017
 */
public interface ItemDescService {
    TbItemDesc queryItemDescByItemId(Long itemId);
}
