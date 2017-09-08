package com.zerods.mall.content.service.impl;

import com.Response.result.ResponseResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zerods.mall.common.pojo.EasyUIDataGridResult;
import com.zerods.mall.content.service.ContentService;
import com.zerods.mall.mapper.TbContentMapper;
import com.zerods.mall.pojo.TbContent;
import com.zerods.mall.pojo.TbContentExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author zerods
 * @version 1.0 08/09/2017
 */
@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private TbContentMapper contentMapper;

    @Override
    public EasyUIDataGridResult<TbContent> getContentList(int page, int rows) {
        PageHelper.startPage(page, rows);
        // 执行查询
        TbContentExample example = new TbContentExample();
        List<TbContent> contents = contentMapper.selectByExample(example);

        EasyUIDataGridResult<TbContent> result = new EasyUIDataGridResult<>();
        result.setRows(contents);
        PageInfo<TbContent> pageInfo = new PageInfo<>(contents);
        result.setTotal(pageInfo.getTotal());

        return result;

    }

    @Override
    public ResponseResult addContent(TbContent content) {
        Date d = new Date();
        content.setCreated(d);
        content.setUpdated(d);
        contentMapper.insert(content);

        return ResponseResult.ok();
    }

    @Override
    public List<TbContent> getContentListByCid(long cid) {
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(cid);

        return contentMapper.selectByExampleWithBLOBs(example);
    }
}
