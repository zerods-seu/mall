package com.zerods.mall.service;

import com.zerods.mall.common.pojo.EasyUITeeNode;

import java.util.List;

/**
 * @author zerods
 * @version 1.0 03/09/2017
 */
public interface ItemCatService {
    List<EasyUITeeNode> getItemCatList(long parentId);
}
