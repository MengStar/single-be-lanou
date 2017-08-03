package meng.xing.repository;

import meng.xing.entity.Exam;
import meng.xing.entity.User;
import meng.xing.entity.UserDoneExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(exported = false) //不是rest风格的资源，不能通过url直接获取。必须从controller访问
public interface UserDoneExamRepository extends JpaRepository<UserDoneExam, Long> {
    UserDoneExam findByUserAndExam(User user, Exam exam);
}
