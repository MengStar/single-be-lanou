package meng.xing;

import meng.xing.api.data.BookRestResponitory;
import meng.xing.domain.Book;
import meng.xing.domain.User;
import meng.xing.repository.UserRepository;
import meng.xing.security.UserRoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 首先运行，用作数据库初始化
 * 会清空数据库，不要在生成环境下运行！！！！！！！！！！！！！！！！！！！！！！！！！！！
 *
 */
@Component
public class DatabaseLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final BookRestResponitory bookRestResponitory;
    private final AuthenticationManager authenticationManager;
    private final String username ="admin";
    private final String password ="admin";

    @Autowired
    public DatabaseLoader(UserRepository userRepository,BookRestResponitory bookRestResponitory,AuthenticationManager authenticationManager){
        this.userRepository=userRepository;
        this.bookRestResponitory=bookRestResponitory;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void run(String... strings) throws Exception {
        initUserTable();
        //新建管理员账户，并授权
        // 方便其操作数据库
        User admin = new User(username,password,UserRoleEnum.ROLE_USER.toString(),UserRoleEnum.ROLE_ADMIN.toString());
        userRepository.save(admin);
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        initBookTable();

        //删除管理员账户，取消授权
        SecurityContextHolder.getContext().setAuthentication(null);
        //userRepository.delete(admin);

    }



    /**
     * 初始化usr表,调用的Service层，
     * 外部是无法访问到service的 权限放在api层
     */
    private void initUserTable(){
        userRepository.deleteAll(); //清空
        List<User>users = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            User user = new User("meng" + i, "meng" + i, UserRoleEnum.ROLE_USER.toString());
            users.add(user);
        }
        users.forEach(user -> userRepository.save(user));
    }
    /**
     * 初始化book表 ,直接调用RestAPI
     * 需要权限
     */
    private void initBookTable(){
        bookRestResponitory.deleteAll(); //清空
        Date date = new Date();
       List<Book> books = new ArrayList<>();
        long[] types = {1L,2L,3L};
        for(int i = 0; i<20; i++){
            Book book = new Book("name"+i,"author"+i,"press"+i,date,"abstract"+i);
            book.setBokTypeIds(types);
            book.setCallNumber("ABC123");
            book.setStoreTime(date);
            books.add(book);
        }
        books.forEach(book -> bookRestResponitory.save(book));
    }
}
