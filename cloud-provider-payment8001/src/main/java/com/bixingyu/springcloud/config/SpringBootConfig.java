package com.bixingyu.springcloud.config;

import ch.qos.logback.classic.pattern.MessageConverter;
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

    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
                converters.add(new HL7MessageConverter());
            }
        };

    }
}
