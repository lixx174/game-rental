package com.jinx.domain.order;

import lombok.Builder;
import lombok.Getter;

/**
 * @author Jinx
 */
@Getter
@Builder
public class Order {

    private String id;
    private String userId;
    private String productId;
    private OrderStatus status;
    private RentalPeriod rentalPeriod;
    private PricingSnapshot pricingSnapshot;
    private PaymentSnapshot paymentSnapshot;
    private CancelSnapshot cancelTime;

    public void pay(){
        // TODO 走领域事件？
        if(status == OrderStatus.PENDING){

            if(status == OrderStatus.RENTING){

            }
            if(status == OrderStatus.OVERDUE){

            }
        }

        throw new UnsupportedOperationException("该订单无法取消 当前状态[%s]".formatted(status));
    }
}
