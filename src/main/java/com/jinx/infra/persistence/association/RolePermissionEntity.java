package com.jinx.infra.persistence.association;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Jinx
 */
@Getter
@Setter
public class RolePermissionEntity {
    private String roleId;
    private String permissionId;
}
