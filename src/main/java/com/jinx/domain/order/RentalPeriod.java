package com.jinx.domain.order;

import lombok.Getter;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

/**
 * @author Jinx
 */
@Getter
public class RentalPeriod {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public RentalPeriod(LocalDateTime startTime, LocalDateTime endTime) {
        // 开放式租赁时没有结束时间
        Assert.notNull(startTime, "非法租赁开始时间");

        this.startTime = startTime;
        this.endTime = endTime;
    }
}
