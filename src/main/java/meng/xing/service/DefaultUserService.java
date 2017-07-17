package meng.xing.service;

import meng.xing.entity.User;
import meng.xing.entity.UserRole;
import meng.xing.repository.UserRepository;
import meng.xing.repository.UserRoleReponsitory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * 用户管理Service
 * 部分方法可缓存
 */
@Service
public class DefaultUserService implements UserService {
    private final Logger logger = LoggerFactory.getLogger(DefaultUserService.class);
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserRoleReponsitory userRoleReponsitory;

    @Override
    public boolean deleteByUsername(String username) {
        return userRepository.deleteByUsername(username) != 0;

    }

    @Override
    @Cacheable(value = "UserService", key = "#username") //可以缓存
    public User findUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return null;
        }
        return user;
    }

    @Override
    public Page<User> findAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public boolean setUserRoles(String username, String... roles) {

        Set<UserRole> _roles = new HashSet<>();
        for (int i = 0; i < roles.length; i++) {
            _roles.add(userRoleReponsitory.findByRole(roles[i]));
        }
        User user = userRepository.findByUsername(username);
        logger.info(_roles.toString());
        user.setRoles(_roles);
        return userRepository.saveAndFlush(user) != null;
    }
}
