package meng.xing.service;

import meng.xing.model.User;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;
    User dave;
    @Before
    public  void initUsr(){
        User user = new User();
        user.setUsername("dave");
        user.setPassword("123");
        user.setRole(2);
        user.setCreatedate(new Date());
        dave = user;
    }
    @Test
    public void addUser() throws Exception {
        userService.addUser(dave);
    }

}