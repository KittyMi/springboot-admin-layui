package com.zhilian.market.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 项目启动更新一下 access_token
 * @author Andy_Lai
 * @date 2019-03-27
 */
@Component
@Slf4j
public class InitServer implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

        log.info("access_token更新成功");
    }
}
