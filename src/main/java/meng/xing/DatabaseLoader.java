package meng.xing;

import meng.xing.api.data.BookRest;
import meng.xing.api.data.BookTypeRest;
import meng.xing.entity.Book;
import meng.xing.entity.BookType;
import meng.xing.entity.User;
import meng.xing.entity.UserRole;
import meng.xing.repository.UserRoleReponsitory;
import meng.xing.security.UserRoleEnum;
import meng.xing.service.UserService;
import meng.xing.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 首先运行，用作数据库初始化
 * 会清空数据库，不要在生成环境下运行！！！！！！！！！！！！！！！！！！！！！！！！！！！
 * 不建议修改 username 和 password，因为测试用例使用 "admin","admin"
 */
@Component
public class DatabaseLoader implements CommandLineRunner {
    private final AuthService authService;
    private final UserService userService;
    private final BookRest bookRest;
    private final AuthenticationManager authenticationManager;
    private final UserRoleReponsitory userRoleReponsitory;
    private final BookTypeRest bookTypeRest;
    private final String username = "admin";
    private final String password = "admin";

    @Autowired
    public DatabaseLoader(BookTypeRest bookTypeRest, UserRoleReponsitory userRoleReponsitory, AuthService authService, BookRest bookRest, AuthenticationManager authenticationManager, UserService userService) {
        this.userRoleReponsitory = userRoleReponsitory;
        this.authService = authService;
        this.bookRest = bookRest;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.bookTypeRest = bookTypeRest;
    }

    @Override
    public void run(String... strings) throws Exception {

        initeRole();//初始化权限表
        User admin = new User(username, password);  //新建管理员账户，并授权 方便其操作数据库
        authService.register(admin);
        userService.setUserRoles(username, UserRoleEnum.ROLE_ADMIN.toString(),
                UserRoleEnum.ROLE_STUDENT.toString(),
                UserRoleEnum.ROLE_TEACHER.toString());

        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        initBookTable();
        initUserTable();

        //删除管理员账户，取消授权
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    /**
     * 初始化usr表,调用的Service层，
     * 外部是无法访问到service的 权限放在api层
     */
    private void initUserTable() {

        List<User> users = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            User user = new User("meng" + i, "meng");
            users.add(user);
        }
        users.forEach(user -> authService.register(user));
    }

    /**
     * 初始化book表 ,直接调用RestAPI
     * 需要权限
     */
    private void initBookTable() {
        if (bookTypeRest.count() == 0) {
            BookType bookType = new BookType("小说");
            bookTypeRest.save(bookType);
        }
        Date date = new Date();
        List<Book> books = new ArrayList<>();
        Set<BookType> types = new HashSet<>();
        types.add(bookTypeRest.findOne(bookTypeRest.findAll().iterator().next().getId()));
        for (int i = 0; i < 20; i++) {
            Book book = new Book("name" + i, "author" + i, "press" + i, date, "abstract" + i);
            book.setBookTypes(types);
            book.setCallNumber("mengmeng" + UUID.randomUUID().toString());
            book.setStoreTime(date);
            books.add(book);

        }
        books.forEach(book -> bookRest.save(book));
    }

    private void initeRole() {
        if (userRoleReponsitory.count() != 0)
            return;
        this.userRoleReponsitory.save(new UserRole("ROLE_DEVELOPER"));
        this.userRoleReponsitory.save(new UserRole("ROLE_ADMIN"));
        this.userRoleReponsitory.save(new UserRole("ROLE_STUDENT"));
        this.userRoleReponsitory.save(new UserRole("ROLE_TEACHER"));
    }
}
