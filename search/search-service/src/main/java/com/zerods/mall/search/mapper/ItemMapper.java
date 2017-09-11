package com.zerods.mall.search.mapper;

import com.zerods.mall.common.pojo.SearchItem;

import java.util.List;

/**
 * @author zerods
 * @version 1.0 11/09/2017
 */
public interface ItemMapper {
    List<SearchItem> getItemList();
}
