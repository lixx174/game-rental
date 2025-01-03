package com.jinx.infra.persistence.entity;

import com.jinx.infra.persistence.AuditEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author Jinx
 */
@Getter
@Setter
public class UserEntity extends AuditEntity {
    private String name;
    private LocalDate birthday;
    private String status;
}
