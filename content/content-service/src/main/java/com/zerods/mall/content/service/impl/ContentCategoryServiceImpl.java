package com.zerods.mall.content.service.impl;

import com.zerods.mall.common.pojo.EasyUITeeNode;
import com.zerods.mall.common.pojo.ResponseResult;
import com.zerods.mall.content.service.ContentCategoryService;
import com.zerods.mall.mapper.TbContentCategoryMapper;
import com.zerods.mall.pojo.TbContentCategory;
import com.zerods.mall.pojo.TbContentCategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zerods
 * @version 1.0 07/09/2017
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;

    @Override
    public List<EasyUITeeNode> getContentCatList(long parentId) {
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria  = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);

        List<TbContentCategory> categories = contentCategoryMapper.selectByExample(example);
        List<EasyUITeeNode> easyUITeeNodes = new ArrayList<>();
        EasyUITeeNode node = null;

        for (TbContentCategory category : categories) {
            node = new EasyUITeeNode();
            node.setId(category.getId());
            node.setState(category.getIsParent() ? "closed" : "open");
            node.setText(category.getName());

            easyUITeeNodes.add(node);
        }

        node = null;
        return easyUITeeNodes;
    }

    @Override
    public ResponseResult addContentCatagory(long parentId, String name) {
        TbContentCategory contentCategory = new TbContentCategory();
        Date d = new Date();
        contentCategory.setCreated(d);
        contentCategory.setUpdated(d);
        contentCategory.setName(name);
        contentCategory.setParentId(parentId);
        contentCategory.setSortOrder(1);

        // status 1-正常 2-删除
        contentCategory.setStatus(1);
        // 新添加的节点一定是叶子结点
        contentCategory.setIsParent(false);
        // 插入到数据库
        contentCategoryMapper.insert(contentCategory);

        // 判断其父节点
        TbContentCategory parent = contentCategoryMapper.selectByPrimaryKey(parentId);
        parent.setIsParent(true);
        contentCategoryMapper.updateByPrimaryKey(parent);

        return ResponseResult.ok(contentCategory);

    }

}
