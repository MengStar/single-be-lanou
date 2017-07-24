package meng.xing.service;

import meng.xing.entity.Paper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Administrator on 2017/7/23.
 */
public interface PaperService {
    Page<Paper> findAllPapers(Pageable pageable);
    boolean addPaper(Paper paper);
    boolean updatePaper(Paper paper);
    boolean deletePaperById(Long paper);
}
