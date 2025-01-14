package com.jinx.infra.persistence.entity;

import com.jinx.infra.persistence.AuditEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Jinx
 */
@Getter
@Setter
public class RoleEntity extends AuditEntity {
    private String name;
    private String userId;
}
