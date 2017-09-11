package com.zerods.mall.search.service.impl;

import com.zerods.mall.common.pojo.ResponseResult;
import com.zerods.mall.common.pojo.SearchItem;
import com.zerods.mall.search.mapper.ItemMapper;
import com.zerods.mall.search.service.SearchItemService;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 导入数据库数据到索引库
 * @author zerods
 * @version 1.0 11/09/2017
 */
@Service
public class SearchItemServiceImpl implements SearchItemService {
    private static final Logger LOG = LoggerFactory.getLogger(SearchItemServiceImpl.class);

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private SolrServer solrServer;

    @Override
    public ResponseResult importAllItems() {
        // 查询商品列表
        List<SearchItem> searchItems = itemMapper.getItemList();
        try {
            // 遍历商品列表
            for (SearchItem searchItem : searchItems) {
                // 创建文档对象,文档中添加域
                SolrInputDocument doc = new SolrInputDocument();
                doc.addField("id", searchItem.getId());
                doc.addField("item_title", searchItem.getTitle());
                doc.addField("sell_point", searchItem.getSell_point());
                doc.addField("item_price", searchItem.getPrice());
                doc.addField("item_category_name", searchItem.getCatagory_name());
                doc.addField("image", searchItem.getImage());
                // 把文档对象写入到索引库
                solrServer.add(doc);
            }
            // 提交
            solrServer.commit();
            return ResponseResult.ok();
        } catch (Exception e) {
            LOG.warn("商品列表导入索引发生错误");
            return ResponseResult.build(500, "数据导入异常");
        }
    }
}
