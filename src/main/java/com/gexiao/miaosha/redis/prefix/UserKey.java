package com.gexiao.miaosha.redis.prefix;

/**
 * @author : gexiao
 * @since : 2019/11/18 9:52
 */
public class UserKey extends BasePrefix {
    private UserKey(String prefix) {
        super(prefix);
    }

    public static UserKey getById = new UserKey("id");
    public static UserKey getByName = new UserKey("name");
}
