package com.jinx.domain.primitive;

import org.springframework.util.Assert;

/**
 * @author Jinx
 */
public record Password(String value) {

    public Password {
        // at least six digits and mixed case
        Assert.hasText(value, "password must have text!");
        Assert.isTrue(
                value.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{6,}$"),
                "illegal password [%s]!".formatted(value)
        );

        // TODO encrypt the text
//        value = Arrays.toString(Base64.getEncoder().encode(value.getBytes()));
    }


    public boolean match(Password password) {
        // TODO check password is same, get string directly?
        return false;
    }
}
