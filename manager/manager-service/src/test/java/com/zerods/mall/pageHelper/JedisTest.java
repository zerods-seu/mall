package com.zerods.mall.pageHelper;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zerods
 * @version 1.0 08/09/2017
 */
public class JedisTest {

    @Test
    public void testJedis() throws Exception {
        // 创建一个连接Jedis对象，参数，host， port
         Jedis jedis = new Jedis("192.168.56.109", 6379);

         jedis.auth("123456");
         jedis.set("test123", "my first jedis test");
         String s = jedis.get("test123");
        System.out.println(s);

        jedis.close();
    }

    @Test
    public void testJedisPool() throws Exception {
        JedisPool jedisPool = new JedisPool("192.168.56.109",6379);
        Jedis jedis = jedisPool.getResource();
        String string = jedis.get("test123");
        System.out.println(string);
        jedis.close();
        jedisPool.close();
    }

    @Test
    public void testJedisCluster() throws Exception {
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.56.109", 7001));
        nodes.add(new HostAndPort("192.168.56.109", 7002));
        nodes.add(new HostAndPort("192.168.56.109", 7003));
        nodes.add(new HostAndPort("192.168.56.109", 7004));
        nodes.add(new HostAndPort("192.168.56.109", 7005));
        nodes.add(new HostAndPort("192.168.56.109", 7006));

        JedisCluster jedisCluster = new JedisCluster(nodes);
        jedisCluster.set("test", "123");
        String s = jedisCluster.get("test");
        System.out.println(s);
        jedisCluster.close();
    }
}
