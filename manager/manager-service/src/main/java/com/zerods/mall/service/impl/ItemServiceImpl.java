package com.zerods.mall.service.impl;

import com.zerods.mall.mapper.TbItemMapper;
import com.zerods.mall.pojo.TbItem;
import com.zerods.mall.pojo.TbItemExample;
import com.zerods.mall.pojo.TbItemExample.Criteria;
import com.zerods.mall.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zerods
 * @version 1.0 01/09/2017
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper itemMapper;

    @Override
    public TbItem getItemById(long id) {
        // 根据主键查询
//        return itemMapper.selectByPrimaryKey(id);

        // 根据条件查询
        TbItemExample example = new TbItemExample();
        Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);

        List<TbItem> items = itemMapper.selectByExample(example);
        items.forEach(System.out::println);
        if (items != null && items.size() > 0)
            return items.get(0);
        return  null;
    }
}
