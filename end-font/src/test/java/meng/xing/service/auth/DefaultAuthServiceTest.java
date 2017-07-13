package meng.xing.service.auth;

import meng.xing.domain.User;
import meng.xing.security.JwtTokenUtil;
import meng.xing.security.JwtUserDetails;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/7/13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultAuthServiceTest {


    @Autowired
    AuthService authService;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    List<User> users;

    //构造多个user对象
    @Before
    public void initUser() {
        users = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            User user = new User("dave" + i, "dave" + i, "ROLE_ADMIN", "ROLE_USER");
            users.add(user);
        }
    }

    @Test
    public void register() throws Exception {
        users.forEach(user -> authService.register(user));
        assert authService.register(users.get(0)) == null;
    }

    @Test
    public void login() throws Exception {
        String token = authService.login("dave0", "dave0");
        String ret = token;
        ret += "---name--" + jwtTokenUtil.getUsernameFromToken(token);
        ret += "--created--" + jwtTokenUtil.getCreatedDateFromToken(token);
        ret += "--exp--" + jwtTokenUtil.getExpirationDateFromToken(token);
        ret += "--new--" + jwtTokenUtil.refreshToken(token);
        ret += "--canRefresh--" + jwtTokenUtil.canTokenBeRefreshed(token, new Date());
        System.out.println("-------------------------------------------------------------------------------------------------------------------"
                + ret + "-------------------------------------------------------------------------------------------------------------------");
        assert jwtTokenUtil.getUsernameFromToken(token).equals("dave0");
    }

    @Test
    public void refresh() throws Exception {
        String token = authService.login("dave0", "dave0");
        assert !authService.refresh(token).isEmpty();
    }
}