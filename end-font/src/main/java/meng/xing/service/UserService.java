package meng.xing.service;

import meng.xing.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
     User findUserByUsername(String username);
     Page<User> findAllUsers(Pageable pageable);
}
