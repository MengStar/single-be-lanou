package meng.xing.service.auth;

import meng.xing.domain.User;
import meng.xing.security.JwtTokenUtil;
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
    private AuthService authService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;



    @Test
    public void register() throws Exception {
        int i=888;
        User user = new User("dave" + i, "dave" + i, "ROLE_ADMIN", "ROLE_USER");
        assert authService.register(user) != null;//第一次成功
        assert authService.register(user) == null;//第二次失败
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