package com.jinx.application.factory;

import com.jinx.AppConfiguration;
import com.jinx.application.UserContext;
import com.jinx.application.command.OrderCreateCommand;
import com.jinx.application.generator.IdentityGenerator;
import com.jinx.application.generator.Schema;
import com.jinx.domain.order.Order;
import com.jinx.domain.order.OrderStatus;
import com.jinx.domain.order.PricingSnapshot;
import com.jinx.domain.order.RentalPeriod;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Jinx
 */
@Component
@RequiredArgsConstructor
public class OrderFactory{

    private final IdentityGenerator identityGenerator;
    private final AppConfiguration appConfiguration;

    public Order create(OrderCreateCommand command){
        return Order.builder()
                .id(identityGenerator.generate(Schema.ORDER))
                .userId(UserContext.getUserId())
                .productId(command.getProductId())
                .status(OrderStatus.PENDING)
                .rentalPeriod(new RentalPeriod(command.getStartTime(), command.getEndTime()))
                .pricingSnapshot(
                        new PricingSnapshot(
                                // TODO 走统一的枚举反序列化 需要抛出可读异常
//                                command.getRentalType(),
                                null,
                                command.getUnitPrice(),
                                appConfiguration.getBusiness().getDeposit()
                        )
                )
                .build();
    }
}
