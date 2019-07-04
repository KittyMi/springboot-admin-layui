package com.zhilian.market.redis;

import org.springframework.stereotype.Service;

/**
 * redis 接收处理
 */
@Service
public class RedisReceiver {


    public void receiveMessage(String message) {
        //这里是收到通道的消息之后执行的方法
        System.out.println("收到消息"+message);
    }

}
