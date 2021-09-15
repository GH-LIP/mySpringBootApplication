package com.jedis;

import redis.clients.jedis.Jedis;

import java.util.Set;

public class JedisTest {
    public static void main(String[] args) {
        /**
         * 使用 Jedis 需要做哪些准备？
         *      1. 首先，关闭redis服务器的防火墙
         *      2. 导入所需jedis的jar包（当前为maven项目，加入jedis依赖即可）
         *      3. 创建Jedis对象，配置服务器和端口号
         *      4， 运行jedis.ping();返回结果为 PONG 即连接成功
         */
        Jedis jedis = new Jedis("127.0.0.1", 6379);
//        String ping = jedis.ping();   // 验证是否能连上redis服务器
//        System.out.println("ping结果："+ping);

        /**
         * Jedis 对redis常见数据类型的操作
         *      String、List、Set、Hash、Zset....
         */

        /**
         * Jedis-API:    Key
         */
        jedis.set("k1", "v1");
        jedis.set("k2", "v2");
        jedis.set("k3", "v3");
        Set<String> keys = jedis.keys("*");
        System.out.println(keys.size());
        for (String key : keys) {
            System.out.println("redis 的键：" + key);
        }
        System.out.println("redis 是否存在键k1：" + jedis.exists("k1"));
        System.out.println("redis 是否过期：" + jedis.ttl("k1")); //
        System.out.println("redis 获取键k1的值：" + jedis.get("k1"));

    }
}
