package com.jinx.infra.persistence.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jinx.domain.repository.PermissionRepository;
import com.jinx.domain.user.Permission;
import com.jinx.domain.user.PermissionType;
import com.jinx.domain.user.User;
import com.jinx.infra.persistence.association.AssociationMapper;
import com.jinx.infra.persistence.association.RolePermissionEntity;
import com.jinx.infra.persistence.association.UserRoleEntity;
import com.jinx.infra.persistence.converter.UserConverter;
import com.jinx.infra.persistence.entity.PermissionEntity;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * FIXME This is an aggregate repository that need to prevent circular dependence.
 * FIXME How to prevent?  Only basic repository can be depended.
 *
 * @author Jinx
 */
@Repository
@RequiredArgsConstructor
public class DefaultPermissionRepository implements PermissionRepository {

    private final PermissionMapper target;
    private final AssociationMapper<UserRoleEntity> urMapper;
    private final AssociationMapper<RolePermissionEntity> rpMapper;
    private final UserConverter converter;

    @Override
    public Collection<Permission> findByUser(@Nonnull User user) {
        Set<String> roleIds = urMapper.selectList(
                        Wrappers.lambdaQuery(UserRoleEntity.class)
                                .eq(UserRoleEntity::getUserId, user.getId())
                )
                .stream()
                .map(UserRoleEntity::getRoleId)
                .collect(Collectors.toSet());

        if (!roleIds.isEmpty()) {
            Set<String> permissionIds = rpMapper.selectList(
                            Wrappers.lambdaQuery(RolePermissionEntity.class)
                                    .in(RolePermissionEntity::getRoleId, roleIds)
                    )
                    .stream()
                    .map(RolePermissionEntity::getPermissionId)
                    .collect(Collectors.toSet());

            if (!permissionIds.isEmpty()) {
                return target.selectByIds(permissionIds)
                        .stream()
                        .map(this::initPermission)
                        .collect(Collectors.toList());
            }
        }

        return Collections.emptySet();
    }

    @Override
    public Collection<Permission> findAll() {
        return null;
    }


    @Mapper
    interface PermissionMapper extends BaseMapper<PermissionEntity> {

    }

    private Permission initPermission(PermissionEntity entity) {
        return new Permission(
                entity.getId(),
                entity.getName(),
                PermissionType.valueOf(entity.getType()),
                entity.getSignage()
        );
    }
}
