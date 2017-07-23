package meng.xing.service;

import meng.xing.entity.Paper;
import meng.xing.repository.PaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/7/23.
 */
@Service
public class DefaultPaper implements PaperService{
    @Autowired
    PaperRepository paperRepository;
    @Override
    public List<Paper> findAllPaper() {
        return paperRepository.findAll();
    }

    @Override
    public boolean add(Paper paper) {
        return
                paperRepository.save(paper) != null;
    }
}
