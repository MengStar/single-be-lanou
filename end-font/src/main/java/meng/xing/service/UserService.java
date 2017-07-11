package meng.xing.service;

import meng.xing.repository.UserRepository;
import meng.xing.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/7/11.
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void addUser(User user){
        userRepository.save(user);
    }
    public Iterable<User> findUserByUsername(){
        return userRepository.findAll();
    }
}
