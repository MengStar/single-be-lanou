package meng.xing.service;

import meng.xing.entity.Paper;

import java.util.List;

/**
 * Created by Administrator on 2017/7/23.
 */
public interface PaperService {
    List<Paper> findAllPaper();
    boolean add(Paper paper);
}
