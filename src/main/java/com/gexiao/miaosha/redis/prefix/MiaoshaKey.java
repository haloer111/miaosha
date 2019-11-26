package com.gexiao.miaosha.redis.prefix;

/**
 * @author : gexiao
 * @since : 2019/11/18 9:52
 */
public class MiaoshaKey extends BasePrefix {
    private MiaoshaKey(String prefix) {
        super(prefix);
    }

    public static MiaoshaKey getGoodsStock = new MiaoshaKey("gs");
    public static MiaoshaKey hasMiaoshaOrderByGoodsIdUserId = new MiaoshaKey("has_goodsId_userId");
}
