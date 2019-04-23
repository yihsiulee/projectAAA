package com.allpass.projectAAA;

import com.allpass.projectAAA.Security.WebSecurityConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
@ComponentScan({ "com.allpass.projectAAA.Controller.*" })
@Import({ WebSecurityConfig.class })
public class AppConfig {

    @Bean(name = "dataSource")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.h2.Driver");
        driverManagerDataSource.setUrl("jdbc:h2:tcp://localhost/~/database/h2");
        driverManagerDataSource.setUsername("george");
        driverManagerDataSource.setPassword("cool3210");
        return driverManagerDataSource;
    }
}