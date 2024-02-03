package com.brankomicroservices.customer;

import com.brankomicroservices.amqp.RabbitMQMessageProducer;
import com.brankomicroservices.clients.fraud.FraudCheckResponse;
import com.brankomicroservices.clients.fraud.FraudClient;
import com.brankomicroservices.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();
        //todo: check if email valid
        //todo: check if email not taken
        customerRepository.saveAndFlush(customer);  //da bi sacekao da sacuva i da bi onda imali id

        //todo: check if fraudster
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("Fraudster");
        }


        //todo: send notification
        NotificationRequest notificationRequest =  new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                "Hi welcome..."
        );
        rabbitMQMessageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
        );
    }
}
