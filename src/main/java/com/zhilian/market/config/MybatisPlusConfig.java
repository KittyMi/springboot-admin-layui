package com.zhilian.market.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * 数据源的相关配置
 * @author Andy_Lai
 * @date 2019-03-17
 */
@Configuration
@MapperScan("com.zhilian.market.mapper*")
public class MybatisPlusConfig {
    /**
     * 分页插件，自动识别数据库类型
     * 多租户，请参考官网【插件扩展】
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    @Bean
    @Profile("dev")
    public PerformanceInterceptor performanceInterceptor()
    {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        performanceInterceptor.setMaxTime(1000*10);
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }
}
