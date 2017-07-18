package meng.xing.api.data;

import meng.xing.entity.Book;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 根路径为“data”，写在配置文件中
 * data rest 风格的 api，返回的json含有相关信息
 * get post put patch delete 请求对应不同的操作
 * 在security的保护下，查看只需要登录
 * 修改 删除 需要管理员权限
 * 可以直接操作数据，不通过controller
 */
@Repository
@RepositoryRestResource(path = "books")
public interface BookRest extends PagingAndSortingRepository<Book, Long> {
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    void delete(@Param("book") Book book);
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    void delete(@Param("id") Long id);
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    Book save(@Param("book") Book book);

    @Transactional
    long deleteByCallNumber(String callNumber);
}
