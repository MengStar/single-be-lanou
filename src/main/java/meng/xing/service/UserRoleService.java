package meng.xing.service;

import meng.xing.entity.UserRole;

import java.util.Set;

public interface UserRoleService {
    Set<UserRole> findALlRoles();
    UserRole findUserRoleByRole(String role);
}
