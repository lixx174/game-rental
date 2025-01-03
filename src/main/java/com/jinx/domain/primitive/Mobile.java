package com.jinx.domain.primitive;

import org.springframework.util.Assert;

/**
 * @author Jinx
 */
public record Mobile(String value) {

    public Mobile {
        // only support domestic mobile
        Assert.hasText(value, "mobile must have text!");
        Assert.isTrue(value.matches("^1[3-9]\\d{9}$"), "illegal mobile!");
    }

    /**
     * Get the last six digits of the phone number.
     *
     * @return six digits number string
     */
    public String getLastSixDigits() {
        return value.substring(value.length() - 6);
    }
}
