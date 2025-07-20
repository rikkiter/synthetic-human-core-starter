package com.lamov.config;

import com.lamov.service.RobotService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SyntheticHumanCoreStarterProperties.class)
public class SyntheticHumanCoreStarterAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public RobotService robotService() {
        return new RobotService();
    }
}
