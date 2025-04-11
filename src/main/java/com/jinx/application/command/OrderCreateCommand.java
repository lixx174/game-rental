package com.jinx.application.command;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Jinx
 */
@Getter
@Setter
public class OrderCreateCommand {

    @NotEmpty(message = "商品id不能为空")
    private String productId;

    @NotNull(message = "开始时间不能为空")
    @Future(message = "开始时间不能小于当前时间")
    private LocalDateTime startTime;
    /**
     * 如果选择开放式租赁 则不传该字段
     */
    private LocalDateTime endTime;

    @NotEmpty(message = "租赁方式不能为空")
    private String rentalType;

    @NotNull(message = "单价不能为空")
    @Min(value = 0, message = "单价不能小于0")
    private BigDecimal unitPrice;
}
