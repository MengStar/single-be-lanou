package meng.xing.repository;

import meng.xing.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/7/23.
 */
@Repository
@RepositoryRestResource(exported = false) //不是rest风格的资源，不能通过url直接获取。必须从controller访问
public interface AnswerRepository extends JpaRepository<Answer,Long> {

    List<Answer> findByUserIdAndExamId(long userId, long examId);
}
