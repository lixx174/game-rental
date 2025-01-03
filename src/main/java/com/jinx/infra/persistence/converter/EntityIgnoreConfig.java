package com.jinx.infra.persistence.converter;

import com.jinx.infra.persistence.AuditEntity;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.MappingInheritanceStrategy;
import org.mapstruct.Mappings;

/**
 * @author Jinx
 */
@MapperConfig(
        mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_ALL_FROM_CONFIG
)
public interface EntityIgnoreConfig {

    @Mappings({
            @Mapping(target = "createAt", ignore = true),
            @Mapping(target = "createBy", ignore = true),
            @Mapping(target = "updateAt", ignore = true),
            @Mapping(target = "updateBy", ignore = true),
            @Mapping(target = "deleted", ignore = true),
    })
    @SuppressWarnings("unused")
    AuditEntity config(Object object);
}
