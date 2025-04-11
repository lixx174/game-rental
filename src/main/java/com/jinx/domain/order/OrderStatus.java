package com.jinx.domain.order;

/**
 * @author Jinx
 */
public enum OrderStatus {
    /**
     * 待支付
     */
    PENDING,
    /**
     * 已取消
     */
    CANCELLED,
    /**
     * 租用中
     */
    RENTING,
    /**
     * 已归还
     */
    RETURNED,
    /**
     * 超时未归还
     */
    OVERDUE,
    /**
     * 异常订单	用户申诉 / 系统检测异常
     */
    EXCEPTION
}
