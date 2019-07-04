package com.zhilian.market;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 项目启动入口
 * @author Andy_Lai
 * @date 2019-04-08
 */
@EnableTransactionManagement
@SpringBootApplication
@Slf4j
@ComponentScan(
        basePackages = {
                "com.zhilian.market.**"}
)
@EnableScheduling
@EnableSwagger2
public class BootstrapApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

        return application.sources(BootstrapApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(BootstrapApplication.class, args);
        log.info("Bootstrap is success!");
    }

}