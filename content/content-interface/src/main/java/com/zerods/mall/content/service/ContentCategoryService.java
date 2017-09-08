package com.zerods.mall.content.service;

import com.zerods.mall.common.pojo.EasyUITeeNode;
import com.zerods.mall.common.pojo.ResponseResult;

import java.util.List;

/**
 * 内容类别服务接口
 * @author zerods
 * @version 1.0 07/09/2017
 */
public interface ContentCategoryService {
    List<EasyUITeeNode> getContentCatList(long parentId);

    ResponseResult addContentCatagory(long parentId, String name);
}
