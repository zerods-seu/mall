package com.zerods.mall.service.impl;

import com.zerods.mall.common.pojo.EasyUITeeNode;
import com.zerods.mall.mapper.TbItemCatMapper;
import com.zerods.mall.pojo.TbItemCat;
import com.zerods.mall.pojo.TbItemCatExample;
import com.zerods.mall.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品分类管理
 * @author zerods
 * @version 1.0 03/09/2017
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper itemCatMapper;

    /**
     * 根据parentId查询子节点列表
     * @param parentId
     * @return
     */
    @Override
    public List<EasyUITeeNode> getItemCatList(long parentId) {
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);

        List<TbItemCat> itemCats = itemCatMapper.selectByExample(example);
        List<EasyUITeeNode> treeNodes = new ArrayList<>();
        EasyUITeeNode node = null;
        for (TbItemCat cat : itemCats) {
            node = new EasyUITeeNode();
            node.setId(cat.getId());
            node.setText(cat.getName());
            node.setState(cat.getIsParent() ? "closed" : "open");
            treeNodes.add(node);
        }
        node = null;
        return treeNodes;
    }
}
