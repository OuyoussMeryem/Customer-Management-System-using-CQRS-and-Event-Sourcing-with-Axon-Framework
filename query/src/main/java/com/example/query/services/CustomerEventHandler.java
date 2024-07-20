package com.example.query.services;

import com.example.query.entities.Customer;
import com.example.query.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.ansa.apicommoncore.events.CustomerCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
@Slf4j
public class CustomerEventHandler {

    private CustomerRepository customerRepository;

    @EventHandler
    public void on(CustomerCreatedEvent customerCreatedEvent){
        log.info("CustomerCreatedEvent eventHandler");
        Customer customer=new Customer();
        customer.setId(customerCreatedEvent.getId());
        customer.setName(customerCreatedEvent.getName());
        customer.setEmail(customerCreatedEvent.getEmail());
        customerRepository.save(customer);
    }

}
