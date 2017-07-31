package meng.xing.service;

import meng.xing.entity.Exam;
import meng.xing.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultExam implements ExamService {
    @Autowired
    ExamRepository examRepository;
    @Override
    public Page<Exam> findAllExams(Pageable pageable) {
        return examRepository.findAll(pageable);
    }

    @Override
    public Exam findExamById(Long id) {
        return examRepository.findOne(id);
    }

    @Override
    @Transactional
    public boolean addExam(Exam exam){
        if (examRepository.save(exam) != null)
            return true;
        else return false;
    }

    @Override
    @Transactional
    public boolean updateExam(Exam exam) {
        if (examRepository.save(exam) != null)
            return true;
        else return false;
    }

    @Override
    @Transactional
    public boolean deleteExamById(Long id) {
        examRepository.delete(id);
        return true;
    }
}
