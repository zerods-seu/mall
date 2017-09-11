package com.zerods.mall.solrj;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * @author zerods
 * @version 1.0 11/09/2017
 */
public class TestSolrJ {

    @Test
    public  void addDocument() throws Exception {
        // 创建SolrServer对象，创建一个连接
        SolrServer solrServer = new HttpSolrServer("http://192.168.56.110:8080/solr/collection1");
        // 创建一个文档对象SolrInputDocument
        SolrInputDocument document = new SolrInputDocument();
        // 向文档对象中添加域，文档中必须包含一个id域，所有的域名必须在schema.xml中定义
        document.addField("id", "doc01");
        document.addField("item_title", "测试商品01");
        document.addField("item_price", 1000L);

        // 把文档写入索引库
        solrServer.add(document);
        // 提交
        solrServer.commit();
    }

    @Test
    public  void deleteDocument() throws Exception {
        // 创建SolrServer对象，创建一个连接
        SolrServer solrServer = new HttpSolrServer("http://192.168.56.110:8080/solr/collection1");

        //删除文档
//        solrServer.deleteById("doc01");
        solrServer.deleteByQuery("id:doc01");

        // 提交
        solrServer.commit();
    }
}
