package com.zerods.mall.jedis;

import com.zerods.mall.common.jedis.JedisClient;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zerods
 * @version 1.0 08/09/2017
 */
public class JedisClientTest {

    @Test
    public void testJedisClient() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "classpath:spring/applicationContext-redis.xml");

        JedisClient jedisClient = context.getBean(JedisClient.class);
        jedisClient.set("mytest", "jedisClient");
        String s = jedisClient.get("mytest");
        System.out.println(s);


    }
}
