package com.merchant.portal.configuration;

import com.merchant.portal.data.TransactionRepository;
import com.merchant.portal.service.MerchantService;
import com.merchant.portal.service.MerchantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties
@EnableJpaRepositories(basePackages = {"com.merchant.portal.data"})
public class MerchantConfiguration {

    private TransactionRepository transactionRepository;

    @Autowired
    public MerchantConfiguration(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }


    @Bean
    MerchantService merchantService(){
        return new MerchantServiceImpl(transactionRepository);
    }

}
