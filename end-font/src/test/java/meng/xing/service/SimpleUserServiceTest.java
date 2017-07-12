package meng.xing.service;

import meng.xing.model.User;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleUserServiceTest {

    @Autowired
    private UserService userService;
    List<User> users;

    //构造多个user对象
    @Before
    public  void initUser(){
        users = new ArrayList<>();
        for (int i=0;i<20;i++) {
            User user = new User();
            user.setUsername("dave"+i);
            user.setPassword("123");
            user.setRole("ADMIN");
            user.setCreateDate(new Date());
            user.setLastPasswordResetDate(new Date());
            users.add(user);
        }
    }
    @Test
    public void register() throws Exception {
        users.forEach(user -> userService.register(user));
        assertThat(userService.register(users.get(0))).isFalse();
    }
    @Test
    public void findUserByUsername() throws Exception {
        assertThat(userService.findUserByUsername("dave0")).isNotNull();
        assertThat(userService.findUserByUsername("")).isNull();
    }

}