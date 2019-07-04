package com.zhilian.market.config;


import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.descriptor.web.ErrorPage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Spring MVC 配置
 */
@Configuration
@Slf4j
public class AdminWebMvcConfigurer implements WebMvcConfigurer
{

    /**
     * 静态资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/enclosure/**").addResourceLocations("file:D:\\upload\\");
        //registry.addResourceHandler("/enclosure/**").addResourceLocations("file:/usr/mp/upload/");
    }

    /**
     * 解决跨域问题
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("*");
    }
    /**
     * 统一处理没啥业务逻辑处理的controller请求，实现代码的简洁
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("403").setViewName("error/403");
        registry.addViewController("404").setViewName("error/404");
        registry.addViewController("500").setViewName("error/500");
        registry.addViewController("/").setViewName("login");
        registry.addViewController("login").setViewName("login");
        registry.addViewController("index").setViewName("index");
        registry.addViewController("role").setViewName("role/list");
        registry.addViewController("business").setViewName("business/list");
        registry.addViewController("user").setViewName("user/list");
        registry.addViewController("menu").setViewName("menu/list");
        registry.addViewController("mini/menu").setViewName("mini/menu/list");
        registry.addViewController("project").setViewName("project/list");
        registry.addViewController("classes").setViewName("classes/list");
        registry.addViewController("scorePage").setViewName("classes/scorePage");
        registry.addViewController("scorePage").setViewName("classes/score");
        registry.addViewController("case").setViewName("case/list");
        registry.addViewController("classes").setViewName("classes/lists");
        registry.addViewController("exam/quest").setViewName("exam/quest_list");
        registry.addViewController("exam/list").setViewName("exam/list");
        registry.addViewController("exam/socre").setViewName("exam/score_list");
        registry.addViewController("exam/socre/quest").setViewName("exam/score_quest_list");
        registry.addViewController("faq").setViewName("faq/list");


    }
    /**
     * 使用fastjson做为json的解析器
     * @return
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //  fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastJsonConfig.setSerializeFilters((ValueFilter) (o, s, source) -> {
            if (source == null) {
                return "";
            }
            return source;
        });

        fastConverter.setFastJsonConfig(fastJsonConfig);
        HttpMessageConverter<?> converter = fastConverter;
        return new HttpMessageConverters(converter);
    }

//    /**
//     * ContentNegotiatingViewResolver
//     * @param configurer
//     */
//    @Override
//    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//        configurer.defaultContentType(MediaType.TEXT_HTML)
//                .ignoreAcceptHeader(true)
//                .favorPathExtension(true);
//    }
}
