package meng.xing.repository;

import meng.xing.model.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//jpa自动实现接口，默认实现了常见的方法，比如save(),findAll()等等
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
     User findByUsername(String username);
     Page<User> findAll(Pageable pageable);
}