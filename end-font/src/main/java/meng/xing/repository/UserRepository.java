package meng.xing.repository;

import meng.xing.domain.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

//jpa自动实现接口，默认实现了常见的方法，比如save(),findAll()等等
@Repository
@RepositoryRestResource(exported = false)
public interface UserRepository extends JpaRepository<User, Long> {
     User findByUsername(String username);
     Page<User> findAll(Pageable pageable);
}