package com.brankomicroservice.fraud;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class FraudServices {
    private final FraudRepository fraudRepository;

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
