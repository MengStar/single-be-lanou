package meng.xing.service;

import meng.xing.entity.UserRole;
import meng.xing.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class DefauleUserRole implements  UserRoleService {
    @Autowired
    UserRoleRepository userRoleRepository;
    @Override
    public Set<UserRole> findALlRoles() {
        return (Set<UserRole>) userRoleRepository.findAll();
    }
    @Override
    public UserRole findUserRoleByRole(String role) {
        return userRoleRepository.findByRole(role);
    }
}
