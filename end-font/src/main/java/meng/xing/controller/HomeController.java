package meng.xing.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {
    @GetMapping("/")
    public String index() {
        return "hello word!";
    }

    /**
     * 用来测试security
     * 请求需要在头部附加[{"key":"Authorization","value":"bms 23","description":""}]
     * 建议用postman操作
     * @return
     */
    @GetMapping("/test")
    public String test() {
        StringBuffer str = new StringBuffer();
        str.append( "username:" + ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        SecurityContextHolder.getContext().getAuthentication().getAuthorities().forEach(auth->str.append("role:"+auth));
      SecurityContextHolder.getContext().getAuthentication().getAuthorities().forEach(auth-> System.out.println(auth.getAuthority()));
        return str.toString();
    }
}
