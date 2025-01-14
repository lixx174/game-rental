package com.jinx.infra.persistence.entity;

import com.jinx.infra.persistence.AuditEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Jinx
 */
@Getter
@Setter
public class PermissionEntity extends AuditEntity {
    private String name;
    private String type;
    private String signage;
}
