package meng.xing.service;

import meng.xing.entity.Paper;
import meng.xing.entity.Subject;
import meng.xing.entity.TestItem;
import meng.xing.entity.User;
import meng.xing.repository.SubjectRepository;
import meng.xing.repository.TestItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2017/7/23.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultPaperTest {
    final Logger logger = LoggerFactory.getLogger(DefaultPaperTest.class);
    @Autowired
    PaperService paperService;
    @Autowired
    TestItemRepository testItemRepository;
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    UserService userService;

    @Test
    @Transactional //一定要用事物包裹
    public void findAllPaper() throws Exception {
        logger.info(paperService.findAllPaper().get(0).getUsers().toString());
    }

    @Test
    public void add() {
        Set<TestItem> items = new HashSet<>();
        items.addAll(testItemRepository.findAll());
        Subject subject = subjectRepository.findByType("WEB");
        logger.info(String.valueOf(subject.getId()));
        Set<User>users = new HashSet<>();
        User user = userService.findUserByUsername("admin");
        users.add(user);
        logger.info(String.valueOf(users.isEmpty()));
        paperService.add(new Paper("dasdasdasdas",subject,users,items));
    }

}