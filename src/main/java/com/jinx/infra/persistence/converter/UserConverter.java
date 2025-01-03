package com.jinx.infra.persistence.converter;

import com.jinx.domain.user.User;
import com.jinx.infra.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

/**
 * @author Jinx
 */
@Mapper(config = EntityIgnoreConfig.class)
public interface UserConverter {

    UserEntity convert(User user);
}
