package meng.xing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import meng.xing.api.data.BookRest;
import meng.xing.api.data.BookTypeRest;
import meng.xing.entity.*;
import meng.xing.entity.testItem.ChoiceItem;
import meng.xing.entity.testItem.TestItemType;
import meng.xing.repository.*;
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
    private final UserRoleRepository userRoleRepository;
    private final BookTypeRest bookTypeRest;
    private final SubjectRepository subjectRepository;
    private final TestItemRepository testItemRepository;
    private final PaperRepository paperRepository;
    private final ExamRepository examRepository;

    private final String username = "admin";
    private final String password = "admin";

    @Autowired
    public DatabaseLoader(PaperRepository paperRepository, ExamRepository examRepository,
                          SubjectRepository subjectRepository, BookTypeRest bookTypeRest, TestItemRepository testItemRepository
            , UserRoleRepository userRoleRepository, AuthService authService,
                          BookRest bookRest, AuthenticationManager authenticationManager, UserService userService) {
        this.userRoleRepository = userRoleRepository;
        this.authService = authService;
        this.bookRest = bookRest;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.bookTypeRest = bookTypeRest;
        this.subjectRepository = subjectRepository;
        this.testItemRepository = testItemRepository;
        this.paperRepository = paperRepository;
        this.examRepository = examRepository;
    }

    @Override
    public void run(String... strings) throws Exception {

        initRole();//初始化权限表
        initSubject();//初始化考试类型表
        User admin = new User(username, password, "萌萌", "13086695953", "6415@qq.com", "四川省 成都市 郫县", true, 18);  //新建管理员账户，并授权 方便其操作数据库
        authService.register(admin);
        userService.setUserRoles(username, UserRoleEnum.ROLE_ADMIN.toString(),
                UserRoleEnum.ROLE_STUDENT.toString(),
                UserRoleEnum.ROLE_TEACHER.toString());

        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        initBookTable();
        initUserTable();
        initTestItem();
        initPaper();
        initExam();
        // initExam();
        //取消授权
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    /**
     * 初始化usr表,调用的Service层，
     * 外部是无法访问到service的 权限放在api层
     */
    private void initUserTable() {

        List<User> users = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            User user = new User("meng" + i, "meng", "刘星", "13086695951", "6415@qq.com", "四川省 成都市 郫县", false, 18);
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

    private void initRole() {
        if (userRoleRepository.count() != 0)
            return;
        this.userRoleRepository.save(new UserRole("ROLE_DEVELOPER"));
        this.userRoleRepository.save(new UserRole("ROLE_DEFAULT"));
        this.userRoleRepository.save(new UserRole("ROLE_ADMIN"));
        this.userRoleRepository.save(new UserRole("ROLE_STUDENT"));
        this.userRoleRepository.save(new UserRole("ROLE_TEACHER"));
    }

    private void initSubject() {
        if (subjectRepository.count() != 0)
            return;
        subjectRepository.save(new Subject("JAVA"));
        subjectRepository.save(new Subject("WEB"));
        subjectRepository.save(new Subject("C++"));
        subjectRepository.save(new Subject("PHP"));
        subjectRepository.save(new Subject("Mysql"));
        subjectRepository.save(new Subject("Scala"));

    }

    private void initTestItem() throws JsonProcessingException {
        Map<String, String> items = new HashMap<>();
        items.put("A", "asda");
        items.put("B", "ds");
        items.put("C", "sd");
        items.put("D", "asdda");
        ChoiceItem choiceItem = new ChoiceItem("题干", items);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(choiceItem);

        TestItem t1 = new TestItem(TestItemType.QUESTION.toString(), "请简述JAVAb编译过程", "答案无");
        TestItem t2 = new TestItem(TestItemType.CHOICE.toString(), json, "A");
        t1.setSubject(subjectRepository.findByType("JAVA"));
        t2.setSubject(subjectRepository.findByType("WEB"));

        testItemRepository.save(t1);

        testItemRepository.save(t2);
    }

    private void initPaper() {
        Set<TestItem> items = new HashSet<>();
        items.addAll(testItemRepository.findAll());
        Subject subject = subjectRepository.findByType("JAVA");
        Set<User> users = new HashSet<>();
        User user = userService.findUserByUsername("admin");
        paperRepository.save(new Paper("测试试卷", subject, user, items));
    }

    private void initExam() {
        Subject java = subjectRepository.findByType("JAVA");
        Subject scala = subjectRepository.findByType("Scala");
        User user = userService.findUserByUsername("admin");
        Paper paper = paperRepository.findOne((long) 1);
        for (int i = 0; i < 40; i++) {
            examRepository.save(new Exam("这是一场测试考试" + i, java, paper, user));
        }
        for (int i = 0; i < 30; i++) {
            examRepository.save(new Exam("这是一场测试考试" + i, scala, paper, user));
        }
    }
}
