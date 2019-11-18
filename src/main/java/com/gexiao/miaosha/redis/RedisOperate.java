package com.gexiao.miaosha.redis;

import com.alibaba.fastjson.JSON;
import com.gexiao.miaosha.redis.prefix.KeyPrefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * @author : gexiao
 * @since : 2019/11/15 15:57
 */
@Component
public class RedisOperate {
    @Autowired
    private JedisPool jedisPool;

    /**
     * 获取单个对象
     *
     * @param prefix
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T get(KeyPrefix prefix, String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //加上前缀
            key = prefix.getPrefix() + key;
            String value = jedis.get(key);

            T bean = stringToBean(value, clazz);
            return bean;
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 获取多个对象
     * @param prefix
     * @param key
     * @param clazz
     * @return
     */
    public <T> List<T> getArray(KeyPrefix prefix, String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //加上前缀
            key = prefix.getPrefix() + key;
            String value = jedis.get(key);

            List<T> beans = stringToArray(value, clazz);
            return beans;
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 存放
     *
     * @param prefix
     * @param key    键
     * @param value  值
     * @return
     */
    public <T> Boolean set(KeyPrefix prefix, String key, T value) {
        Jedis jedis = null;
        try {
            if (value == null) {
                return false;
            }
            key = prefix.getPrefix() + key;
            jedis = jedisPool.getResource();
            int expire = prefix.expireSeconds();
            if (expire <= 0) {
                jedis.set(key, beanToString(value));
            } else {
                jedis.setex(key, expire, beanToString(value));
            }
            return true;
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 是否存在
     *
     * @param prefix
     * @param key    键
     * @return
     */
    public Boolean exists(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            key = prefix.getPrefix() + key;
            jedis = jedisPool.getResource();
            return jedis.exists(key);
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 增加
     *
     * @param prefix
     * @param key    键
     * @return
     */
    public Long incr(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            key = prefix.getPrefix() + key;
            jedis = jedisPool.getResource();
            return jedis.incr(key);
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 减少
     *
     * @param prefix
     * @param key    键
     * @return
     */
    public Long decr(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            key = prefix.getPrefix() + key;
            jedis = jedisPool.getResource();
            return jedis.decr(key);
        } finally {
            returnResource(jedis);
        }
    }


    /**
     * 关闭jedis，并返回连接池
     *
     * @param jedis
     */
    private void returnResource(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    private static <T> String beanToString(T value) {
        String s = JSON.toJSONString(value);
        return s;
    }

    private static <T> T stringToBean(String value, Class<T> clazz) {
        if (value == null) {
            return null;
        }
        T parse = (T) JSON.parseObject(value, clazz);
        return parse;
    }

    private static <T> List<T> stringToArray(String value, Class<T> clazz) {
        if (value == null) {
            return null;
        }
        List<T> parse = (List<T>) JSON.parseArray(value, clazz);
        return parse;
    }
}
