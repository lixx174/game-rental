package com.jinx.app.infra.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.jinx.app.application.SecuritySupport;
import com.jinx.app.domain.Audit;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.type.LocalDateTimeTypeHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;

import static com.jinx.app.domain.Audit.CREATE_AT;
import static com.jinx.app.domain.Audit.CREATE_BY;
import static com.jinx.app.domain.Audit.UPDATE_AT;
import static com.jinx.app.domain.Audit.UPDATE_BY;

/**
 * The mp config to support curd.
 *
 * @author Jinx
 */
@Import({
        LocalDateTimeTypeHandler.class,
})
@Configuration
@ConditionalOnClass(Audit.class)
public class MybatisPlusConfig implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        strictInsertFill(metaObject, CREATE_AT, LocalDateTime::now, LocalDateTime.class);
        strictInsertFill(metaObject, CREATE_BY, SecuritySupport::getUserIdOrDefault, String.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        strictUpdateFill(metaObject, UPDATE_AT, LocalDateTime::now, LocalDateTime.class);
        strictUpdateFill(metaObject, UPDATE_BY, SecuritySupport::getUserIdOrDefault, String.class);
    }
}