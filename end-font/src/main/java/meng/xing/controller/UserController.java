package meng.xing.controller;

import meng.xing.model.User;
import meng.xing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@RestController 表示是rest风格，返回的对象直接转化成json
@RestController
public class UserController{
    @Autowired
    private UserService userService;
    @RequestMapping("/")
    public Iterable<User> index(@RequestParam(value = "username" ,defaultValue="admin")String username){


        return userService.findUserByUsername();

    }

}