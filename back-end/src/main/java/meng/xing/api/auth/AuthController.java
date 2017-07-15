package meng.xing.api.auth;

import meng.xing.entity.User;
import meng.xing.security.UserRoleEnum;
import meng.xing.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

/**
 * 开放api，不用权限，用于用户注册，登录，获取token
 * Created by Administrator on 2017/7/13.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private  AuthService authService;

    /**
     * 登录并返回token
     * @param username
     * @param password
     * @return  成功：token 200;失败：null 200
     */
    @PostMapping("/login")
    public String  login(@RequestParam("username")String username,@RequestParam("password")String password ){
        return authService.login(username,password);
    }

    /**
     * 用户注册
     * @param username
     * @param password
     * @return 成功：json 200 ;失败：null 200
     */
    @PostMapping("/register")
    public User register(@RequestParam("username")String username, @RequestParam("password")String password ){
        User user =new User(username,password, UserRoleEnum.ROLE_USER.toString());
        return authService.register( user);
    }

    /**
     * 刷新token
     * @param token
     * @return 成功：新的token 200 ,失败：null 200
     */
    @PostMapping ("/refresh")
    public  String refresh(@RequestParam("token") String token ){
        return authService.refresh(token);
    }
}
