package meng.xing.api.auth;

import meng.xing.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/7/13.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private  AuthService authService;
    @PostMapping("/login")
    public String  login(@RequestParam("username")String username,@RequestParam("password")String password ){
        return authService.login(username,password);
    }
}
