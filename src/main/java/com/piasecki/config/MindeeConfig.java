package com.piasecki.config;

import com.mindee.MindeeClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MindeeConfig {

    @Value("${mindee.key}")
    private String accessKey;

    @Bean
    public MindeeClient mindeeClient(){
        return new MindeeClient(accessKey);
    }
}
