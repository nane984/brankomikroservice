package com.brankomikroservice.fraud;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/fraud-check")
public class FraudController {

    private final FraudServices fraudServices;

    public FraudController(FraudServices fraudServices){
        this.fraudServices = fraudServices;
    }

    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId){
        boolean result = fraudServices.isFraudulentCustomer(customerId);

        return new FraudCheckResponse(result);
    }
}
