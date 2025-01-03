package com.jinx.infra.persistence.entity;

import com.jinx.infra.persistence.AuditEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Jinx
 */
@Getter
@Setter
public class AccountEntity extends AuditEntity {
    private String mobile;
    private String password;
    private String status;
}
