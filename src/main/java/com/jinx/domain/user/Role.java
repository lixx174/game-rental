package com.jinx.domain.user;

import lombok.Getter;
import org.springframework.util.Assert;

/**
 * @author Jinx
 */
@Getter
public class Role {

    private String id;
    private String name;

    public Role(String id, String name) {
        Assert.notNull(id, "id mustn't be null!");
        Assert.hasText(name, "name mustn't be empty!");
        this.id = id;
        this.name = name;
    }
}
