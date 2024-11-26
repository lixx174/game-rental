package com.jinx.app.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Audit fields, assigned before persistence by ORM.
 *
 * @author jinx
 */
@Getter
@Setter
public abstract class Audit {

    private LocalDateTime createAt;
    private String createBy;
    private LocalDateTime updateAt;
    private String updateBy;
}
