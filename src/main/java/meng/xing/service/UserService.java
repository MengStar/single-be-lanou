package meng.xing.service;

import meng.xing.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
     boolean deleteByUsername(String username);
     User findUserByUsername(String username);
     Page<User> findAllUsers(Pageable pageable);
}
