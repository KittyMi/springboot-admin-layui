package com.zhilian.market.config;

import io.github.biezhi.wechat.api.constant.Config;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

@Configuration
public class WeChatBotConfig {
    @Bean
    public Config    config() {
        Config config=Config.me().autoLogin(true).showTerminal(false);
        return config;
    }
}
