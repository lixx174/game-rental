package com.jinx.infra.persistence;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
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
public abstract class AuditEntity extends Entity {

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createAt;

    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateAt;

    @TableField(fill = FieldFill.UPDATE)
    private String updateBy;
}
