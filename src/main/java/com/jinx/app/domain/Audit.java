package com.jinx.app.domain;

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
public abstract class Audit {

    public static String CREATE_AT = "createAt";
    public static String CREATE_BY = "createBy";
    public static String UPDATE_AT = "updateAt";
    public static String UPDATE_BY = "updateBy";

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createAt;

    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateAt;

    @TableField(fill = FieldFill.UPDATE)
    private String updateBy;
}
