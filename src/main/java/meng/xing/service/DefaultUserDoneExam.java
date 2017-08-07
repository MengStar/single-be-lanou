package meng.xing.service;

import meng.xing.entity.Exam;
import meng.xing.entity.User;
import meng.xing.entity.UserDoneExam;
import meng.xing.repository.UserDoneExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserDoneExam implements UserDoneExamService {
    @Autowired
    UserDoneExamRepository userDoneExamRepository;

    @Override
    public boolean add(UserDoneExam userDoneExam) {
        if (userDoneExamRepository.save(userDoneExam) != null)
            return true;
        else return false;
    }

    @Override
    public boolean isExist(User user, Exam exam) {
        if (userDoneExamRepository.findByUserAndExam(user, exam) != null)
            return true;
        else return false;
    }


}
