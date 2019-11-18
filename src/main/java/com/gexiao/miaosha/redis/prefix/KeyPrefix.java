package com.gexiao.miaosha.redis.prefix;

/**
 * redis存放key的前缀
 * @author : gexiao
 * @since : 2019/11/18 9:44
 */
public interface KeyPrefix {
    /**
     * 超时时间，单位秒
     * @return
     */
    int expireSeconds();

    /**
     * 获取前缀
     * @return
     */
    String getPrefix();
}
