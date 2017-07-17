package meng.xing.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRoleReponsitoryTest {
    final Logger logger = LoggerFactory.getLogger(UserRoleReponsitoryTest.class);
    @Autowired
    UserRoleReponsitory userRoleReponsitory;

    @Test
    @Transactional //一定要用事物包裹
    public void findByRole() throws Exception {
        logger.info(userRoleReponsitory.findAll().toString());
        logger.info(userRoleReponsitory.findByRole("ROLE_ADMIN").toString());
        logger.info(userRoleReponsitory.findByRole("ROLE_ADMIN").getUsers().toString());
    }

}