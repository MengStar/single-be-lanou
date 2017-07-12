package meng.xing.service;

import meng.xing.model.User;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Date;

import static org.assertj.core.api.Java6Assertions.assertThat;

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
    public  void initUser(){
        User user = new User();
        user.setUsername("dave");
        user.setPassword("123");
        user.setRole("ADMIN");
        user.setCreateDate(new Date());
        user.setLastPasswordResetDate(new Date());
        dave = user;
    }

    @Test
    //执行第一次时，测试不通过
    public void register() throws Exception {
        assertThat(userService.register(dave)).isFalse();
    }
    @Test
    public void findUserByUsername() throws Exception {
        assertThat(userService.findUserByUsername("dave")).isNotNull();
        assertThat(userService.findUserByUsername("")).isNull();
    }

}