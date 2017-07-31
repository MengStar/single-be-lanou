package meng.xing.api.normol;

import meng.xing.entity.UserRole;
import meng.xing.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/userRoles")
public class UserRoleController {
    @Autowired
    UserRoleService userRoleService;
    @GetMapping
    public Set<UserRole> findAllRole(){
        return userRoleService.findALlRoles();
    }
}
