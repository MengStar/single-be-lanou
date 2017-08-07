package meng.xing.service;

import meng.xing.entity.Exam;
import meng.xing.entity.User;
import meng.xing.entity.UserDoneExam;

public interface UserDoneExamService {
    boolean add(UserDoneExam userDoneExam);
    boolean isExist(User user, Exam exam);
}
