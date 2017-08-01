package meng.xing.api.auth;

import meng.xing.entity.User;
import meng.xing.security.UserRoleEnum;
import meng.xing.service.UserService;
import meng.xing.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 开放api，不用权限，用于用户注册，登录，获取token
 * Created by Administrator on 2017/7/13.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;

    /**
     * 登录并返回token
     *
     * @return 成功：token 200;失败：null 200
     */
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody(required = true) Map<String, Object> map) {
        String username = map.get("username").toString();
        String password = map.get("password").toString();
        Map<String, Object> retObj = new HashMap<>();
        retObj.put("token", authService.login(username, password));
        return retObj;
    }

    /**
     * 用户注册
     *
     * @return 成功：token 200 ;失败：null 200
     */
    @PostMapping("/register")
    @Transactional
    public Map<String, Object> register(@RequestBody(required = true) Map<String, Object> map) {
        Map<String, Object> retObj = new HashMap<>();
        String username = map.get("username").toString();
        //用户名存在
        if (userService.findUserByUsername(username) != null) {
            retObj.put("message", "用户名已经存在");
            return retObj;
        }
        String password = map.get("password").toString();
        String nickName = map.get("nickName").toString();
        String phone = map.get("phone").toString();
        String email = map.get("email").toString();
        String address = map.get("address").toString();
        boolean female = (boolean) map.get("female");
        int age = (int) map.get("age");
        User user = new User(username, password, nickName, phone, email, address, female, age);
        authService.register(user);
        userService.setUserRoles(username, UserRoleEnum.ROLE_DEFAULT.toString());

        retObj.put("token", authService.login(username, password));
        retObj.put("username", username);
        return retObj;
    }

    /**
     * 刷新token
     *
     * @param token
     * @return 成功：新的token 200 ,失败：null 200
     */
    @PostMapping("/refresh")
    public String refresh(@RequestParam("token") String token) {
        return authService.refresh(token);
    }
}
