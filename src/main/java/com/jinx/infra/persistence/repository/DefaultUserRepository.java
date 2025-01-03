package com.jinx.infra.persistence.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jinx.domain.repository.UserRepository;
import com.jinx.domain.user.User;
import com.jinx.infra.persistence.entity.UserEntity;
import com.jinx.infra.persistence.converter.UserConverter;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

/**
 * @author Jinx
 */
@Repository
@RequiredArgsConstructor
public class DefaultUserRepository implements UserRepository {

    private final UserMapper target;
    private final UserConverter converter;

    @Override
    public void save(@NonNull User user) {
        target.insert(converter.convert(user));
    }


    @Mapper
    interface UserMapper extends BaseMapper<UserEntity> {

    }
}
