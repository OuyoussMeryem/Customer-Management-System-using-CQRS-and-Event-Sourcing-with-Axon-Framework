package com.example.query.services;

import com.example.query.entities.Customer;
import com.example.query.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.ansa.apicommoncore.queries.GetAllCustomers;
import ma.ansa.apicommoncore.queries.GetCustomerById;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerQueryHandler {

    private CustomerRepository customerRepository;

    @QueryHandler
    public List<Customer> getALLCustomer(GetAllCustomers getAllCustomers){
        return customerRepository.findAll();
    }

    @QueryHandler
    public Customer getALLCustomer(GetCustomerById getCustomerById){
        return customerRepository.findById(getCustomerById.getId()).orElseThrow(()->new RuntimeException("Customer not found !!"));
    }
}
