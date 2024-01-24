package com.brankomikroservice.fraud;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FraudServices {
    private final FraudRepository fraudRepository;
    public FraudServices(FraudRepository fraudRepository){
        this.fraudRepository = fraudRepository;
    }

    public boolean isFraudulentCustomer(Integer customerId){
        Fraud fraud = Fraud.builder()
                .customerId(customerId)
                .ifFraudster(false)
                .createdAt(LocalDateTime.now())
        .build();
        fraudRepository.save(fraud);
        return false;
    }

}
