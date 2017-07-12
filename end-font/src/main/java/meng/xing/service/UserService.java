package meng.xing.service;

import meng.xing.repository.UserRepository;
import meng.xing.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public boolean register(User user){
        //若用户名存在
        if(findUserByUsername(user.getUsername())!=null){
            return false;
        }
        userRepository.save(user);
        return true;
    }
    //根据用户名返回User对象，只返回一个
    @Cacheable(value = "UserService",key = "#username+'findUserByUsername'") //缓存
    public User findUserByUsername( String username){
        if (userRepository.findByUsername(username).isEmpty()){
            return null;
        }
        return (User)(userRepository.findByUsername(username)).get(0);
    }
}
