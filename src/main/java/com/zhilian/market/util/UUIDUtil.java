package com.zhilian.market.util;

import java.util.UUID;

/**
 * @author ly
 * @date 2017/12/25 14:43
 * @desc uuid util
 */
public class UUIDUtil {
    private UUIDUtil(){}

    /**
     * 生成uuid
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
