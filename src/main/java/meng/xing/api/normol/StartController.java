package meng.xing.api.normol;

import meng.xing.entity.*;
import meng.xing.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
    @Autowired
    PaperService paperService;

    @PostMapping("/start")
    public Map<String, Object> start() {
        return null;
    }

    @PostMapping("/end")
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
                String key = it.next().toString();
                Long testItemId = Long.valueOf(key);
                String answer = answers.get(key);
                startService.complete(new Answer(answer, uId, eId, pId, testItemId));
            }
        }

    }

    @GetMapping("/{examId}")
    public Map<String, Object> complete(@PathVariable("examId") String examId) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findUserByUsername(userDetails.getUsername());
        Exam exam = examService.findExamById(Long.valueOf(examId));
        /**
         * 如果这份答案存在，那么拼接处合适的试卷返回
         */
        if (userDoneExamService.isExist(user, exam)) {
            Paper paper = exam.getPaper();
            //调用一下使得懒加载会加载相关数据
            paper.getTestItems().isEmpty();
            List<Answer> answerList = startService.findAnswersByExamIdAndUserID(exam.getId(), user.getId());
            Map<String, Object> data = new HashMap<>();
            data.put("paper", paper);
            data.put("answers", answerList);
            return data;
        }
        return null;
    }

}
