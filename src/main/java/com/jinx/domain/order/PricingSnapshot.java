package com.jinx.domain.order;

import lombok.Getter;

import java.math.BigDecimal;

/**
 * @author Jinx
 */
@Getter
public class PricingSnapshot {

    private PricingType type;
    private BigDecimal unitPrice;
    private BigDecimal deposit;


    public PricingSnapshot(PricingType type, BigDecimal unitPrice, BigDecimal deposit) {
        this.type = type;
        this.unitPrice = unitPrice;
        this.deposit = deposit;
    }
}
