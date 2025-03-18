package org.example.concurrency.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Slf4j
@Configuration
public class RedisUtil {


    public static JedisPool getJedis(@Value("${spring.data.redis.host}") String host,
                                     @Value("${spring.data.redis.port}") int port) {
        return new JedisPool(host, port);
    }

    public static void main(String[] args) {
        JedisPool jedisPool = RedisUtil.getJedis();

        Jedis jedis = jedisPool.getResource();
        log.info("redis value {}", jedis.get("1"));
        jedis.close();
    }

    private static JedisPool getJedis() {
        return new JedisPool("192.168.91.50", 6379);
    }
}
