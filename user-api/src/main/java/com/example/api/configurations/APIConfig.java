package com.example.api.configurations;

import com.deloop.user.core.configurations.CoreConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({CoreConfig.class})
public class APIConfig {

}
