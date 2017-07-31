package meng.xing.service;

import meng.xing.entity.Exam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExamService {
    Page<Exam> findAllExams(Pageable pageable);

    Exam findExamById(Long id);

    boolean addExam(Exam exam);

    boolean updateExam(Exam exam);

    boolean deleteExamById(Long exam);


}
