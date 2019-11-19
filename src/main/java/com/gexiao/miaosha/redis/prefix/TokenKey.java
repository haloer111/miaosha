package com.gexiao.miaosha.redis.prefix;

/**
 * @author : gexiao
 * @since : 2019/11/18 9:52
 */
public class TokenKey extends BasePrefix {
    public final static String TOKEN = "token";
    /**
     * 默认过期时间30分钟
     */
    private static final int DEFAULT_TOKEN_EXPIRE = 60 * 30;

    public TokenKey(String prefix) {
        super(DEFAULT_TOKEN_EXPIRE, prefix);
    }

    public static TokenKey token = new TokenKey(TOKEN);

}
