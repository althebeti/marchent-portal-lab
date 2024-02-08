package com.merchant.portal.configuration;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Log4j2
@Configuration
public class MerchantDBConfig {

    @Bean(name = "merchant-portal")
    @Primary
    @ConfigurationProperties(prefix = "db-merchant-portal")
    public DataSource merchantDataSource() {
        log.info("merchant Data start connection");
        DriverManagerDataSource mysqlDs;
        mysqlDs = new DriverManagerDataSource();
        mysqlDs.setUrl("jdbc:mysql://localhost:3306/dbMerchantPortal");
        mysqlDs.setUsername("root");
        mysqlDs.setPassword("password");
        return mysqlDs;
    }
}
