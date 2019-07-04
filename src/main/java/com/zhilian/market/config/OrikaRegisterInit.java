package com.zhilian.market.config;


import ma.glasnost.orika.MapperFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 初始化
 * @date 2018-12-12
 * @author Andy_Lai
 */
@Component
public class OrikaRegisterInit implements CommandLineRunner {

    @Resource
    MapperFactory mapperFactory;
    @Override
    public void run(String... args) throws Exception {
        registerConverter();
    }
    private void registerConverter(){

    }
}
