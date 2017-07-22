package meng.xing.api.normol;

import meng.xing.entity.User;
import meng.xing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * user管理api
 */
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;


    /**
     * username获取user信息
     * 鉴权：当前用户 or ADMIN
     *
     * @param username
     * @return 成功：json 200; 失败：json 403
     */
    @GetMapping("/{username}")
    @PreAuthorize("#username == authentication.name or hasRole('ADMIN')")
    public Map<String, Object> getUserByPathVariableUsername(@PathVariable("username") String username) {

        User _user = userService.findUserByUsername(username);
        Map<String, Object> user = new HashMap<>();
        user.put("id", _user.getId());
        user.put("username", _user.getUsername());
        Map<String, Object> permissions = new HashMap<>();
        permissions.put("roles", _user.getRoles().stream().map(userRole -> userRole.getRole()).collect(Collectors.toList()));
        permissions.put("visit", "1,2,21,7,5,51,2,53");
        user.put("permissions", permissions);
        Map<String, Object> data = new HashMap<>();
        data.put("user", user);
        return data;
    }

    /**
     * 分页user查询
     * 鉴权：ADMIN
     * @param page  当前页面
     * @param pageSize  每页大小
     * @param sort  排序字段
     * @param order 排列顺序 ASC or DESC
     * @return 成功：json 200; 失败：json 403
     */
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    //需要ADMIN权限,有个天坑：hasAuthority('ROLE_ADMIN') means the the same as hasRole('ADMIN')
    public Page<User> getAllUsers(@RequestParam(value = "page",required = false,defaultValue = "1") Integer page,
                                  @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
                                  @RequestParam(value = "sort", defaultValue = "id") String sort,
                                  @RequestParam(value = "order", defaultValue = "asc") String order) {

        Sort _sort = new Sort(Sort.Direction.fromString(order), sort);
        //传来的页码是从1开始，而服务器从1开始算
        Pageable pageable = new PageRequest(page-1, pageSize, _sort);
        return userService.findAllUsers(pageable);
    }

}