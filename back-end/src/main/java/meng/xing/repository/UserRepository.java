package meng.xing.repository;

import meng.xing.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * jpa自动实现接口，默认实现了常见的方法，比如save(),findAll()等等
 * 根据方法名自动操作数据库数据
 */
@Repository
@RepositoryRestResource(exported = false) //不是rest风格的资源，不能通过url直接获取。必须从controller访问
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);

    Page<User> findAll(Pageable pageable);

    @Transactional
    long deleteByUsername(String username);
}