package com.jinx.infra.persistence.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jinx.domain.repository.RoleRepository;
import com.jinx.domain.user.Role;
import com.jinx.domain.user.User;
import com.jinx.infra.persistence.converter.UserConverter;
import com.jinx.infra.persistence.entity.RoleEntity;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author Jinx
 */
@Repository
@RequiredArgsConstructor
public class DefaultRoleRepository implements RoleRepository {

    private final RoleMapper target;
    private final UserConverter converter;

    @Override
    public Collection<Role> findByUser(@Nonnull User user) {
        return target.selectList(
                        Wrappers.lambdaQuery(RoleEntity.class)
                                .eq(RoleEntity::getUserId, user.getId())
                )
                .stream()
                .map(this::initRole)
                .collect(Collectors.toList());
    }


    @Mapper
    interface RoleMapper extends BaseMapper<RoleEntity> {

    }


    private Role initRole(RoleEntity entity) {
        return new Role(entity.getId(), entity.getName());
    }
}
