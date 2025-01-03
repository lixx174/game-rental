package com.jinx.infra.persistence.converter;

import com.jinx.domain.user.Account;
import com.jinx.infra.persistence.entity.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author Jinx
 */
@Mapper(
        config = EntityIgnoreConfig.class
)
public interface AccountConverter {

    @Mappings({
            @Mapping(target = "mobile", source = "mobile.value"),
            @Mapping(target = "password", source = "password.value"),
    })
    AccountEntity convert(Account account);
}
