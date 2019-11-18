package com.gexiao.miaosha.redis.prefix;

/**
 * @author : gexiao
 * @since : 2019/11/18 9:46
 */
public abstract class BasePrefix implements KeyPrefix {
    /**
     * 过期时间
     * 0-代表永不过期
     */
    private int expire;

    private String prefix;

    public BasePrefix(int expire, String prefix) {
        this.expire = expire;
        this.prefix = prefix;
    }

    public BasePrefix(String prefix) {
        this.expire = 0;
        this.prefix = prefix;
    }

    @Override
    public int expireSeconds() {
        return expire;
    }

    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className + ":" + prefix;
    }

}
