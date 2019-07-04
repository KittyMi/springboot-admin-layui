package com.zhilian.market.config;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrikaConfig {
    /**
     * 全局的Mapper Factory 默认不拷贝空属性
     * @return
     */
    @Bean
    public MapperFactory mapperFactory() {
        return new DefaultMapperFactory.Builder()
                .mapNulls(false)
                .build();
    }

    @Bean
    public MapperFacade mapperFacade(MapperFactory mapperFactory) {
        return mapperFactory.getMapperFacade();
    }
}
