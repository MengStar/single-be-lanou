package meng.xing.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultUserServiceTest {

    @Autowired
    UserService userService;
    @Test
    public void findUserByUsername() throws Exception {
        assertThat(userService.findUserByUsername("admin")).isNotNull();
        assertThat(userService.findUserByUsername("")).isNull();
    }
    @Test
    public void findAllUsers() throws Exception {
        Sort _sort = new Sort(Sort.Direction.fromString("ASC"), "id");
        Pageable pageable = new PageRequest(0, 10, _sort);
        assertThat(userService.findAllUsers(pageable)).size().isEqualTo(10);
    }
}