package meng.xing.service;

import meng.xing.entity.Answer;
import meng.xing.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultStart implements StartService {
    @Autowired
    AnswerRepository answerRepository;

    @Override
    public void start() {

    }

    @Override
    public void end() {

    }

    @Override
    public boolean complete(Answer answer) {
        if (answerRepository.save(answer) != null)
            return true;
        else return false;
    }

    @Override
    public List<Answer> findAnswersByExamIdAndUserID(Long examId, Long userId) {
        return answerRepository.findByUserIdAndExamId(userId,examId);
    }
}
