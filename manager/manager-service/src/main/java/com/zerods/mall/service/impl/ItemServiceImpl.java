package com.zerods.mall.service.impl;

import com.Response.result.ResponseResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zerods.mall.common.pojo.EasyUIDataGridResult;
import com.zerods.mall.common.utils.IDUtils;
import com.zerods.mall.mapper.TbItemDescMapper;
import com.zerods.mall.mapper.TbItemMapper;
import com.zerods.mall.pojo.TbItem;
import com.zerods.mall.pojo.TbItemDesc;
import com.zerods.mall.pojo.TbItemExample;
import com.zerods.mall.pojo.TbItemExample.Criteria;
import com.zerods.mall.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author zerods
 * @version 1.0 01/09/2017
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private TbItemDescMapper itemDescMapper;

    public TbItem getItemById(long id) {
        // 根据主键查询
//        return itemMapper.selectByPrimaryKey(id);

        // 根据条件查询
        TbItemExample example = new TbItemExample();
        Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);

        List<TbItem> items = itemMapper.selectByExample(example);
        if (items != null && items.size() > 0)
            return items.get(0);
        return  null;
    }

    @Override
    public EasyUIDataGridResult<TbItem> getItemList(int page, int rows) {
        // 执行sql语句之前设置分页信息使用PageHelper的startPage方法
        PageHelper.startPage(page, rows);
        // 执行查询
        TbItemExample example = new TbItemExample();
        List<TbItem> items = itemMapper.selectByExample(example);

        EasyUIDataGridResult<TbItem> result = new EasyUIDataGridResult<>();
        result.setRows(items);
        PageInfo<TbItem> pageInfo = new PageInfo<>(items);
        result.setTotal(pageInfo.getTotal());

        return result;
    }

    @Override
    public ResponseResult addItem(TbItem item, String description) {
        // 补全商品pojo数据，插入
        long id = IDUtils.getItemId();
        item.setId(id);
        Date date = new Date();
        // 1-正常 2-下架 3-删除
        item.setStatus((byte) 1);
        item.setCreated(date);
        item.setUpdated(date);
        itemMapper.insert(item);

        // 创建商品描述的pojo，插入
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(id);
        itemDesc.setItemDesc(description);
        item.setCreated(date);
        item.setUpdated(date);
        itemDescMapper.insert(itemDesc);

        return ResponseResult.ok();
    }
}
