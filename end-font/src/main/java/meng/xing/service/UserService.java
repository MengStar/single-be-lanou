package meng.xing.service;

import meng.xing.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    public User findUserByUsername(String username);
    public Page<User> findAllUsers(Pageable pageable);
}
