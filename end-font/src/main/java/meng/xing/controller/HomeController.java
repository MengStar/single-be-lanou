package meng.xing.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {
    @GetMapping("/")
    public String index() {
        Subject currentUser = SecurityUtils.getSubject();
        System.out.println(currentUser.isAuthenticated());
        return "hellow";
    }
}
