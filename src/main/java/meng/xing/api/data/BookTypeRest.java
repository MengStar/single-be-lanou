package meng.xing.api.data;

import meng.xing.entity.BookType;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "bookTypes")
public interface BookTypeRest extends PagingAndSortingRepository<BookType,Long> {
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    void delete(@Param("bookType") BookType bookType);
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    void delete(@Param("id") Long id);
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    BookType save(@Param("bookType") BookType bookType);
}
