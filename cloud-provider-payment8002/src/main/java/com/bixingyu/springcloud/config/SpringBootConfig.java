package com.bixingyu.springcloud.config;

import com.bixingyu.springcloud.messageconverter.HL7MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author BiXingyu
 * @create 2022-12-21 23:12
 */
@Configuration
public class SpringBootConfig {
//    default void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//    }
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
                converters.add(new HL7MessageConverter());
            }
        };

    }

    @Bean
    public textLambda textLambda(){
        return (a, b, c) -> {
            System.out.println(a + b + c);
        };
    }
}



interface textLambda{
    void text(String a ,String b ,String c);
}