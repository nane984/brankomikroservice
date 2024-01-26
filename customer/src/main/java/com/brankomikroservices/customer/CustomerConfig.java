package com.brankomikroservices.customer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CustomerConfig {

    //create rest template for CustomerService
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
