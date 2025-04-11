package com.jinx.application.service;

import com.jinx.application.command.OrderCreateCommand;
import com.jinx.application.factory.OrderFactory;
import com.jinx.domain.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Jinx
 */
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderFactory factory;

    public void create(OrderCreateCommand command) {
        Order order = factory.create(command);


    }
}
