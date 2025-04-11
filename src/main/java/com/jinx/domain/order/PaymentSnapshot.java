package com.jinx.domain.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Jinx
 */
public class PaymentSnapshot {

    private LocalDateTime payAt;
    /**
     * TODO 值对象？ 记录id + name
     */
    private String payBy;
    private BigDecimal payAmount;
}
