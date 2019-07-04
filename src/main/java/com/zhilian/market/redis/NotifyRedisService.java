package com.zhilian.market.redis;

public interface NotifyRedisService {
    /**
     * 存入
     * @param hKey
     * @param value
     */
    void put(String hKey, String value);

    /**
     * 查询
     * @param hKey
     * @return
     */
    String get(String hKey);

    /**
     * 删除
     * @param hKey
     */
    void delete(String hKey);
}
