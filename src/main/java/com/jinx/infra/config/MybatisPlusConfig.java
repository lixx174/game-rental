package com.jinx.infra.config;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.jinx.application.UserContext;
import com.jinx.infra.persistence.AuditEntity;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.type.LocalDateTimeTypeHandler;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

/**
 * The mp config to support curd.
 *
 * @author Jinx
 */
@Import({
        LocalDateTimeTypeHandler.class,
})
@Configuration
@ConditionalOnClass(AuditEntity.class)
public class MybatisPlusConfig implements MetaObjectHandler, InitializingBean {

    /**
     * 审计字段名
     */
    private String createAt, createBy, updateAt, updateBy;

    @Override
    public void insertFill(MetaObject metaObject) {
        strictInsertFill(metaObject, createAt, LocalDateTime::now, LocalDateTime.class);
        strictInsertFill(metaObject, createBy, UserContext::getUserId, String.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        strictUpdateFill(metaObject, updateAt, LocalDateTime::now, LocalDateTime.class);
        strictUpdateFill(metaObject, updateBy, UserContext::getUserId, String.class);
    }

    @Override
    public void afterPropertiesSet() {
        Class<AuditEntity> target = AuditEntity.class;
        Field[] fields = target.getDeclaredFields();
        Assert.notEmpty(fields, "[%s] hasn't audited properties!".formatted(target.getName()));

        for (Field field : fields) {
            if (field.isAnnotationPresent(TableField.class)) {
                TableField tableField = field.getAnnotation(TableField.class);
                if (tableField.fill() == FieldFill.INSERT) {
                    if (field.getType() == String.class) {
                        createAt = field.getName();
                    } else if (field.getType() == LocalDateTime.class) {
                        createBy = field.getName();
                    }
                } else if (tableField.fill() == FieldFill.UPDATE) {
                    if (field.getType() == String.class) {
                        updateAt = field.getName();
                    } else if (field.getType() == LocalDateTime.class) {
                        updateBy = field.getName();
                    }
                }
            }
        }

        String name = this.getClass().getName();
        Assert.hasText(createAt, "[%s] fail to init, createAt is empty!".formatted(name));
        Assert.hasText(createBy, "[%s] fail to init, createBy is empty!".formatted(name));
        Assert.hasText(updateAt, "[%s] fail to init, updateAt is empty!".formatted(name));
        Assert.hasText(updateBy, "[%s] fail to init, updateBy is empty!".formatted(name));
    }
}