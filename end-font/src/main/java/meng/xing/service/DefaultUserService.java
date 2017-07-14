package meng.xing.service;

import meng.xing.repository.UserRepository;
import meng.xing.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * 用户管理Service
 * 部分方法可缓存
 */
@Service
public class DefaultUserService implements UserService {
    @Autowired
    UserRepository userRepository;

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
}
