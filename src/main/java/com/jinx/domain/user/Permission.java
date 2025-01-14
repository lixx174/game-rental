package com.jinx.domain.user;

import lombok.Getter;
import org.springframework.util.Assert;

/**
 * @author Jinx
 */
@Getter
public class Permission {

    private String id;
    private String name;
    private PermissionType type;
    private String signage;


    public Permission(String id, String name, PermissionType type, String signage) {
        Assert.notNull(id, "id mustn't be null!");
        Assert.hasText(name, "name mustn't be empty!");
        Assert.notNull(type, "type mustn't be null!");
        Assert.hasText(signage, "signage mustn't be empty!");

        this.id = id;
        this.name = name;
        this.type = type;
        this.signage = signage;
    }
}
