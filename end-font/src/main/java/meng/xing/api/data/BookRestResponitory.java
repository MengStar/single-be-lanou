package meng.xing.api.data;

import meng.xing.domain.Book;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/7/14.
 */
@Repository
@PreAuthorize("hasRole('ADMIN')")
public interface BookRestResponitory extends PagingAndSortingRepository<Book,Long>{
}
