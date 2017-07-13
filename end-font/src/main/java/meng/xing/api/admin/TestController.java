package meng.xing.api.admin;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {
    @GetMapping("/")
    public String index() {
        return "hello word!";
    }

    /**
     * 用来测试security
     *  请先获取token
     * 建议用postman操作
     * @return
     */
    @GetMapping("/test")
    public String test() {
        StringBuffer str = new StringBuffer();
        str.append( "username:" + ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        str.append("password:"+((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getPassword());
        str.append("details:"+SecurityContextHolder.getContext().getAuthentication().getDetails().toString());
        SecurityContextHolder.getContext().getAuthentication().getAuthorities().forEach(auth->str.append("role:"+auth));

        return str.toString();
    }
}
