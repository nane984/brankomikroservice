package com.brankomikroservice.fraud;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/fraud-check")
public class FraudController {

    private final FraudServices fraudServices;

    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId){
        boolean result = fraudServices.isFraudulentCustomer(customerId);

        return new FraudCheckResponse(result);
    }
}
