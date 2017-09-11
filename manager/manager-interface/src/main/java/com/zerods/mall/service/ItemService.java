package com.zerods.mall.service;

import com.zerods.mall.common.pojo.EasyUIDataGridResult;
import com.zerods.mall.common.pojo.ResponseResult;
import com.zerods.mall.pojo.TbItem;

/**
 * @author zerods
 * @version 1.0 01/09/2017
 */
public interface ItemService {
    TbItem getItemById(long id);

    EasyUIDataGridResult<TbItem> getItemList(int page, int rows);

    ResponseResult addItem(TbItem item, String desc);
}
