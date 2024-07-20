package com.example.command.controllers;

import lombok.AllArgsConstructor;
import ma.ansa.apicommoncore.commands.CreateCustomerCommand;
import ma.ansa.apicommoncore.dtos.CustomerRequestDto;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@RequestMapping(path="/customer/commands")
@AllArgsConstructor
public class CustomerCommandController {

    private CommandGateway commandGateway;
    private EventStore eventStore;

    @PostMapping(path="/createCustomer")
    public CompletableFuture<String> newCustomer(@RequestBody CustomerRequestDto customerRequestDto){

        return commandGateway.send(new CreateCustomerCommand(
                UUID.randomUUID().toString(),
                customerRequestDto.getName(),
                customerRequestDto.getEmail()

        ));
    }

    @GetMapping(path="/eventStore/{customerId}")
    public Stream eventStore(@PathVariable String customerId){
        return eventStore.readEvents(customerId).asStream();
    }

}
