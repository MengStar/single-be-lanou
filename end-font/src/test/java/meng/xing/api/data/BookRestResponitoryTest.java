package meng.xing.api.data;

import meng.xing.domain.Book;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/14.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRestResponitoryTest {

    @Autowired
    BookRestResponitory bookRestResponitory;
    @Autowired
    AuthenticationManager authenticationManager;

    @Before
    public void init() {
        //和DatabaseLoader中的保持一致
        String username = "admin";
        String password = "admin";
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    public void save() {
        Date date = new Date();
        Book book = new Book("test", "test", "test", date, "test");
        book.setCallNumber("ABC123");
        book.setStoreTime(date);
       assert  bookRestResponitory.save(book)!=null;
    }

    @After
    public void delete() {
        //取消授权
        SecurityContextHolder.getContext().setAuthentication(null);
    }


}