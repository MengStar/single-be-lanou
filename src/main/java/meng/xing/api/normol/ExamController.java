package meng.xing.api.normol;

import meng.xing.entity.Exam;
import meng.xing.entity.Paper;
import meng.xing.entity.Subject;
import meng.xing.entity.User;
import meng.xing.service.ExamService;
import meng.xing.service.PaperService;
import meng.xing.service.SubjectService;
import meng.xing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/exams")
public class ExamController {
    @Autowired
    ExamService examService;
    @Autowired
    PaperService paperService;
    @Autowired
    SubjectService subjectService;
    @Autowired
    UserService userService;

    /**
     * 分页查询
     *
     * @param page
     * @param pageSize
     * @param sort
     * @param order
     * @return
     */
    @GetMapping
    //需要ADMIN权限,有个天坑：hasAuthority('ROLE_ADMIN') means the the same as hasRole('ADMIN')
    public Object findAllExams(@RequestParam(value = "page", defaultValue = "1") Integer page,
                               @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                               @RequestParam(value = "sort", defaultValue = "id") String sort,
                               @RequestParam(value = "order", defaultValue = "asc") String order,
                               @RequestParam(value = "subject", defaultValue = "") String subject,
                               @RequestParam(value = "subjectList[]", required = false) List<String> subjectList) {
        Sort _sort = new Sort(Sort.Direction.fromString(order), sort);
        //传来的页码是从1开始，而服务器从1开始算
        Pageable pageable = new PageRequest(page - 1, pageSize, _sort);

        if (subjectList != null) {
            List<Exam> examList = new ArrayList<>();
            subjectList.forEach(sub -> {
                examList.addAll(examService.findNoPageExamsBySubject(subjectService.findSubjectByType(sub.toString()), pageable));
            });
            return examList;
        } else {

            Subject subjectObj = null;
            if (subject != "") {
                subjectObj = subjectService.findSubjectByType(subject);
            }

            return examService.findAllExamsBySubject(subjectObj, pageable);
        }


    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
    public boolean update(@PathVariable("id") Long id, @RequestBody Map<String, Object> map) {
        Exam exam = examService.findExamById(id);
        Subject subject = subjectService.findSubjectByType(map.get("subject").toString());
        String description = map.get("description").toString();
        if (map.get("paperId") != null) {
            Paper paper = paperService.findPaperById(Long.valueOf(map.get("paperId").toString()));
            exam.setPaper(paper);
        }
        exam.setDescription(description);
        exam.setSubject(subject);
        return examService.updateExam(exam);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
    public boolean create(@RequestBody Map<String, Object> map) {
        Subject subject = subjectService.findSubjectByType(map.get("subject").toString());
        String description = map.get("description").toString();
        Paper paper = null;
        if (map.get("paperId") != null) {
            paper = paperService.findPaperById(Long.valueOf(map.get("paperId").toString()));
        }
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findUserByUsername(userDetails.getUsername());
        Exam exam = new Exam(description, subject, paper, user);
        return examService.addExam(exam);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
    public boolean delete(@PathVariable("id") Long id) {
        return examService.deleteExamById(id);
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@RequestBody Map<String, ArrayList<Long>> map) {
        map.get("ids").forEach(id -> examService.deleteExamById(id));
    }
}
