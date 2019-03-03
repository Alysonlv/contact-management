package com.alv.contactmanagementclient;
/*
 * Created by alysonlv - 2019-03-02
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

@Component
public class ApplicationConfiguration {

    @Value("${database-path}")
    private static String DATABASE_PATH;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
