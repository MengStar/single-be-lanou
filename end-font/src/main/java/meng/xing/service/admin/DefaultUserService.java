package meng.xing.service.admin;

import meng.xing.repository.UserRepository;
import meng.xing.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements UserService {
    @Autowired
    UserRepository userRepository;

    //根据用户名返回User对象，只返回一个
    @Override
    @Cacheable(value = "UserService", key = "#username") //缓存
    public User findUserByUsername(String username) {
        User user =userRepository.findByUsername(username);
        if (user == null) {
            return null;
        }
        return user;
    }
    @Override
    // TODO: 2017/7/12 分页缓存优化
    public Page<User> findAllUsers(Pageable pageable){
        return  userRepository.findAll(pageable);
    }
}
