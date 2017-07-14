package meng.xing.api;

import meng.xing.domain.User;
import meng.xing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
     * @param username
     * @return 成功：json 200; 失败：json 403
     */
    @GetMapping("/{username}")
    @PreAuthorize("#username == authentication.name or hasRole('ADMIN')")
    public User getUserByPathVariableUsername(@PathVariable("username") String username) {
        return userService.findUserByUsername(username);
    }

    /**
     *  分页user查询
     *  鉴权：ADMIN
     * @param page 当前页面
     * @param size 每页大小
     * @param sort 排序字段
     * @param order 排列顺序 ASC or DESC
     * @return 成功：json 200; 失败：json 403
     */
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")  //需要ADMIN权限,有个天坑：hasAuthority('ROLE_ADMIN') means the the same as hasRole('ADMIN')
    public Page<User> getAllUsers(@RequestParam(value = "page", defaultValue = "0") int page,
                                  @RequestParam(value = "size", defaultValue = "10") int size,
                                  @RequestParam(value = "sort", defaultValue = "id") String sort,
                                  @RequestParam(value = "order", defaultValue = "desc") String order) {

        Sort _sort = new Sort(Sort.Direction.fromString(order), sort);
        Pageable pageable = new PageRequest(page, size, _sort);
        return userService.findAllUsers(pageable);
    }

}