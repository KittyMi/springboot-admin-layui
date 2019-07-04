package com.zhilian.market.redis.impl;

import com.zhilian.market.redis.NotifyRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class NotifyRedisServiceImpl implements NotifyRedisService {

    // hkey的分隔符
    private final static String SPILT_CHAT = "|";
    //key值
    private final static String PRE_KEY = "SHIZHAN_Notify";
    //过期时间 半小时 ms
    private  final static Long EXPIRE_TIME = 30*60*1000L;

    private final RedisTemplate<String, String> redisTemplate;
    private final HashOperations<String, String, String> hashOpt;

    @Autowired
    public NotifyRedisServiceImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOpt = redisTemplate.opsForHash();
    }

    @Override
    public void put(String hKey, String value) {
        hashOpt.put(PRE_KEY, hKey, value);
        redisTemplate.expire(PRE_KEY,EXPIRE_TIME, TimeUnit.MILLISECONDS);
    }

    @Override
    public String get(String hKey) {
        //hashOpt.get(PRE_KEY, hKey);
        return hashOpt.get(PRE_KEY, hKey);
    }

    @Override
    public void delete(String hKey) {
        hashOpt.delete(PRE_KEY, hKey);
    }
}
