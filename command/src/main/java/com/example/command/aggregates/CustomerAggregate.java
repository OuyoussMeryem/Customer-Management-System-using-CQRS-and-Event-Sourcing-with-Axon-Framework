package com.example.command.aggregates;

import lombok.extern.slf4j.Slf4j;
import ma.ansa.apicommoncore.commands.CreateCustomerCommand;
import ma.ansa.apicommoncore.events.CustomerCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j
public class CustomerAggregate {
    @AggregateIdentifier
    private String id;
    private String name;
    private String email;

    public CustomerAggregate() {
    }
    @CommandHandler
    public CustomerAggregate(CreateCustomerCommand createCustomerCommand) {
        log.info("**************************");
        log.info("CreateCustomerCommand received ");
        //pas de logic metier a implementer
        AggregateLifecycle.apply(new CustomerCreatedEvent(
                createCustomerCommand.getId(),
                createCustomerCommand.getName(),
                createCustomerCommand.getEmail()
        ));
    }
    @EventSourcingHandler
    public void on(CustomerCreatedEvent customerCreatedEvent){
        log.info("**************************");
        log.info("CustomerCreatedEvent event ");
        this.id=customerCreatedEvent.getId();
        this.name=customerCreatedEvent.getName();
        this.email= customerCreatedEvent.getEmail();
    }
}
