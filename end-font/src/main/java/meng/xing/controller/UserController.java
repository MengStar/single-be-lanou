package meng.xing.controller;

import meng.xing.model.User;
import meng.xing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

//@RestController 表示是rest风格，返回的对象直接转化成json
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public User getUserByPathVariableUsername(@PathVariable("username") String username) {
        return userService.findUserByUsername(username);
    }
    //具有分页的api
    @GetMapping
    //需要ADMIN权限,有个天坑：hasAuthority('ROLE_ADMIN') means the the same as hasRole('ADMIN')
    @PreAuthorize("hasRole('ADMIN')")
    public Page<User> getAllUsers(@RequestParam(value = "page", defaultValue = "0") int page,
                                  @RequestParam(value = "size", defaultValue = "10") int size,
                                  @RequestParam(value = "sort", defaultValue = "id") String sort,
                                  @RequestParam(value = "order", defaultValue = "desc") String order) {

        Sort _sort = new Sort(Sort.Direction.fromString(order), sort);
        Pageable pageable = new PageRequest(page, size, _sort);
        return userService.findAllUsers(pageable);
    }

}