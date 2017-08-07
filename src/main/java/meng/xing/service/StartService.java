package meng.xing.service;

import meng.xing.entity.Answer;

import java.util.List;

public interface StartService {
    void start();

    void end();

    boolean complete(Answer answer);

    List<Answer> findAnswersByExamIdAndUserID(Long examId,Long userId);

}
