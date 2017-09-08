package com.zerods.mall.content.service;

import com.zerods.mall.common.pojo.EasyUIDataGridResult;
import com.zerods.mall.common.pojo.ResponseResult;
import com.zerods.mall.pojo.TbContent;

import java.util.List;

/**
 * @author zerods
 * @version 1.0 08/09/2017
 */
public interface ContentService {
    EasyUIDataGridResult<TbContent> getContentList(int page, int rows);

    ResponseResult addContent(TbContent content);

    List<TbContent> getContentListByCid(long cid);
}
