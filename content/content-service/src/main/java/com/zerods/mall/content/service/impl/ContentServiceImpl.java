package com.zerods.mall.content.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zerods.mall.common.jedis.JedisClient;
import com.zerods.mall.common.pojo.EasyUIDataGridResult;
import com.zerods.mall.common.pojo.ResponseResult;
import com.zerods.mall.content.service.ContentService;
import com.zerods.mall.mapper.TbContentMapper;
import com.zerods.mall.pojo.TbContent;
import com.zerods.mall.pojo.TbContentExample;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author zerods
 * @version 1.0 08/09/2017
 */
@Service
public class ContentServiceImpl implements ContentService {
    private static final Logger LOG = LoggerFactory.getLogger(ContentServiceImpl.class);

    @Value("${CONTENT_LIST}")
    private String CONTENT_LiST;

    @Autowired
    private TbContentMapper contentMapper;

    @Autowired
    private JedisClient jedisClient;

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
        // 缓存同步，删除缓存中对应的数据
        jedisClient.hdel(CONTENT_LiST, content.getCategoryId().toString());

        return ResponseResult.ok();
    }

    @Override
    public List<TbContent> getContentListByCid(long cid) {
        // 查询缓存，如果有就直接响应结果，没有则查询数据库
        try {
            String json = jedisClient.hget(CONTENT_LiST, cid + "");
            if (StringUtils.isNoneBlank(json)) {
                List<TbContent> contents = JSON.parseArray(json, TbContent.class);
                LOG.info("查询缓存成功" + contents);
                return contents;
            }
        } catch (Exception e) {
            LOG.warn("查询缓存失败");
        }

        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(cid);
        List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
        // 结果添加到缓存
        try {
            jedisClient.hset(CONTENT_LiST, cid + "", JSON.toJSONString(list));
        } catch (Exception e) {
            LOG.warn("添加到缓存失败");
        }
        return contentMapper.selectByExampleWithBLOBs(example);
    }
}
