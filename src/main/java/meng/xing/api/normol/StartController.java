package meng.xing.api.normol;

import meng.xing.entity.Answer;
import meng.xing.entity.Exam;
import meng.xing.entity.User;
import meng.xing.entity.UserDoneExam;
import meng.xing.service.ExamService;
import meng.xing.service.StartService;
import meng.xing.service.UserDoneExamService;
import meng.xing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.Map;

@RestController
@RequestMapping("/start")
public class StartController {
    @Autowired
    StartService startService;
    @Autowired
    UserService userService;
    @Autowired
    UserDoneExamService userDoneExamService;
    @Autowired
    ExamService examService;

    @GetMapping
    public Map<String, Object> start() {
        return null;
    }

    @GetMapping("/end")
    public Map<String, Object> end() {
        return null;
    }

    @PostMapping("/complete")
    public void complete(@RequestBody Map<String, Object> map) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findUserByUsername(userDetails.getUsername());

        Long eId = Long.valueOf(map.get("examId").toString());
        Exam exam = examService.findExamById(eId);
        if (!userDoneExamService.isExist(user, exam)) {
            userDoneExamService.add(new UserDoneExam(exam, user));
            Long pId = Long.valueOf(map.get("paperId").toString());
            Long uId = user.getId();
            Map<String, String> answers = (Map<String, String>) map.get("answer");
            Iterator it = answers.keySet().iterator();
            while (it.hasNext()) {
                Long testItemId = Long.valueOf(it.next().toString());
                String answer = answers.get(it.next());
                startService.complete(new Answer(answer, uId, eId, pId, testItemId));
            }
        }

    }
}
