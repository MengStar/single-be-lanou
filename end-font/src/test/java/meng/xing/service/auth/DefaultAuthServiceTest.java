package meng.xing.service.auth;

import meng.xing.domain.User;
import meng.xing.security.JwtTokenUtil;
import meng.xing.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;


/**
 * Created by Administrator on 2017/7/13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultAuthServiceTest {

    @Autowired
    AuthService authService;
    @Autowired
    UserService userService;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Test
    public void register() throws Exception {

        User user = new User("test", "test", "ROLE_ADMIN", "ROLE_USER");
        System.out.println(userService.deleteByUsername("test"));
        assert authService.register(user) != null;//第一次成功
        assert authService.register(user) == null;//第二次失败
    }

    @Test
    public void login() throws Exception {
        String token = authService.login("admin", "admin");
        assert jwtTokenUtil.getUsernameFromToken(token).equals("admin");
    }

    @Test
    public void refresh() throws Exception {
        String token = authService.login("admin", "admin");
        assert !authService.refresh(token).isEmpty();
    }
}