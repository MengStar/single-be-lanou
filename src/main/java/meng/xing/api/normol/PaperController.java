package meng.xing.api.normol;

import meng.xing.entity.Paper;
import meng.xing.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/papers")
public class PaperController {
    @Autowired
    PaperService paperService;
    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @param sort
     * @param order
     * @return
     */
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    //需要ADMIN权限,有个天坑：hasAuthority('ROLE_ADMIN') means the the same as hasRole('ADMIN')
    public Page<Paper> findAllExams(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                    @RequestParam(value = "sort", defaultValue = "id") String sort,
                                    @RequestParam(value = "order", defaultValue = "asc") String order) {
        Sort _sort = new Sort(Sort.Direction.fromString(order), sort);
        //传来的页码是从1开始，而服务器从1开始算
        Pageable pageable = new PageRequest(page - 1, pageSize, _sort);
        return paperService.findAllPapers(pageable);
    }
}
