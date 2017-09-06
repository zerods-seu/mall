package com.zerods.mall.pageHelper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zerods.mall.mapper.TbItemMapper;
import com.zerods.mall.pojo.TbItem;
import com.zerods.mall.pojo.TbItemExample;
import com.zerods.mall.service.ItemService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;


/**
 * @author zerods
 * @version 1.0 03/09/2017
 */
public class PageHelperTest {
    @Test
    public void testPageHelper() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "classpath:spring/applicationContext-dao.xml");
        TbItemMapper itemMapper = applicationContext.getBean(TbItemMapper.class);
        // 执行sql语句之前设置分页信息使用PageHelper的startPage方法
        PageHelper.startPage(1, 10);
        // 执行查询
        TbItemExample example = new TbItemExample();
        List<TbItem> items = itemMapper.selectByExample(example);
        // 取分页信息，PageInfo：1.总计记录数 2.总页数 3.当前页码
        PageInfo<TbItem> pageInfo = new PageInfo<>(items);
        System.out.println(pageInfo.getTotal());
        System.out.println(pageInfo.getPages());
        System.out.println(items.size());
    }
}
