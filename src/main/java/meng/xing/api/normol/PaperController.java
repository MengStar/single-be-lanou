package meng.xing.api.normol;

import meng.xing.entity.Paper;
import meng.xing.entity.Subject;
import meng.xing.entity.TestItem;
import meng.xing.entity.User;
import meng.xing.service.PaperService;
import meng.xing.service.SubjectService;
import meng.xing.service.TestItemService;
import meng.xing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/papers")
public class PaperController {
    @Autowired
    PaperService paperService;
    @Autowired
    SubjectService subjectService;
    @Autowired
    TestItemService testItemService;
    @Autowired
    UserService userService;

    @GetMapping
    public Page<Paper> findAllPaper(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                    @RequestParam(value = "sort", defaultValue = "id") String sort,
                                    @RequestParam(value = "order", defaultValue = "asc") String order) {
        Sort _sort = new Sort(Sort.Direction.fromString(order), sort);
        //传来的页码是从1开始，而服务器从1开始算
        Pageable pageable = new PageRequest(page - 1, pageSize, _sort);
        return paperService.findAllPapers(pageable);
    }

    @GetMapping("/{id}")
    public Map<String, Object> getPaperById(@PathVariable("id") Long id) {
        Paper _paper = paperService.findPaperById(id);
        Map<String, Object> paper = new HashMap<>();
        paper.put("testItems", _paper.getTestItems());
        return paper;
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
    public boolean update(@PathVariable("id") Long id, @RequestBody Map<String, Object> map) {
        Subject subject = subjectService.findSubjectByType(map.get("subject").toString());
        String description = map.get("description").toString();
        Set<TestItem> testItems = new HashSet<>();
        ArrayList ids = (ArrayList) map.get("testItemIds");
        ids.forEach(testItemId -> testItems.add(testItemService.findTestItemById(Long.valueOf((int) testItemId))));
        Paper paper = paperService.findPaperById(id);
        paper.setDescription(description);
        paper.setSubject(subject);
        paper.setTestItems(testItems);
        return paperService.updatePaper(paper);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
    public boolean create(@RequestBody Map<String, Object> map) {
        Subject subject = subjectService.findSubjectByType(map.get("subject").toString());
        String description = map.get("description").toString();
        Set<TestItem> testItems = new HashSet<>();
        ArrayList ids = (ArrayList) map.get("testItemIds");
        ids.forEach(testItemId -> testItems.add(testItemService.findTestItemById(Long.valueOf((int) testItemId))));
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findUserByUsername(userDetails.getUsername());
        Paper paper = new Paper(description, subject, user, testItems);
        return paperService.addPaper(paper);

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
    public boolean delete(@PathVariable("id") Long id) {
        return paperService.deletePaperById(id);
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@RequestBody Map<String, ArrayList<Long>> map) {
        map.get("ids").forEach(id -> paperService.deletePaperById(id));
    }
}
