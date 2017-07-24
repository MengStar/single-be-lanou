package meng.xing.service;

import meng.xing.entity.Exam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DefaultExam implements ExamService {
    @Override
    public Page<Exam> findAllExams(Pageable pageable) {
        return null;
    }

    @Override
    public boolean addExam(Exam exam) {
        return false;
    }

    @Override
    public boolean updateExam(Exam exam) {
        return false;
    }

    @Override
    public boolean deleteExamById(Long exam) {
        return false;
    }
}
