package com.brognara.recipeappuserservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("local-rds")
@Configuration
public class RdsLocalConfiguration {
    // TODO add any config
}
