package com.example.api.configurations;

import com.deloop.user.core.configurations.CoreConfiguration;
import com.deloop.user.data.config.DBRepositoryConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({CoreConfiguration.class})
public class APIConfig {

}
