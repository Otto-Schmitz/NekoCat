package br.com.nekocat.security.domain.role.mapper;

import br.com.nekocat.security.domain.role.Role;
import br.com.nekocat.security.domain.user.Users;
import br.com.nekocat.security.enuns.RoleType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RoleMapper {
    public static List<Role> defaultRoleList(Users user) {
        return new ArrayList<>(Collections.singletonList(defaultRole(user)));
    }

    public static Role defaultRole(Users user) {
        return Role.builder()
                .name(RoleType.USER.getRole())
                .user(user)
                .build();
    }

}
