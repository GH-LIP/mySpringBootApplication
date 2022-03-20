package com.jedis;

import redis.clients.jedis.Jedis;

import java.util.Set;

public class JedisTest {
    private static final String redisUrl = "127.0.0.1";
    private static final int redisPort = 6379;
    public static void main(String[] args) {

        /**
         * 使用 Jedis 需要做哪些准备？
         *      1. 首先，关闭redis服务器的防火墙
         *      2. 导入所需jedis的jar包（当前为maven项目，加入jedis依赖即可）
         *      3. 创建Jedis对象，配置服务器和端口号
         *      4，运行jedis.ping();返回结果为 PONG 即连接成功
         */
        Jedis jedis = new Jedis(redisUrl, redisPort);
        String ping = jedis.ping();   // 验证是否能连上redis服务器，结果为pong则连接成功。
        if (!"pong".equals(ping)) {
            System.out.println("无法连接redis服务器：" + redisUrl + ":" + redisPort);
            return;
        }

        /**
         * Jedis 对redis常见数据类型的操作
         *      String、List、Set、Hash、Zset
         */



    }
}
