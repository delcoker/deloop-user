package com.deloop.user.api.config;

import com.deloop.user.core.config.CoreConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({CoreConfig.class})
public class APIConfig {


}
