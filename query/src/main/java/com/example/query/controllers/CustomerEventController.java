package com.example.query.controllers;

import com.example.query.entities.Customer;
import lombok.AllArgsConstructor;
import ma.ansa.apicommoncore.queries.GetAllCustomers;
import ma.ansa.apicommoncore.queries.GetCustomerById;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(path="/customer/query")
@AllArgsConstructor
public class CustomerEventController {

    private QueryGateway queryGateway;

    @GetMapping(path="/customers")
    public CompletableFuture<List<Customer>> AllCustomer(){
        return queryGateway.query(new GetAllCustomers(),ResponseTypes.multipleInstancesOf(Customer.class));
    }

    @GetMapping(path="/customerById/{id}")
    public CompletableFuture<Customer> getCutomerById(@PathVariable String id){
        return queryGateway.query(new GetCustomerById(id),ResponseTypes.instanceOf(Customer.class));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
