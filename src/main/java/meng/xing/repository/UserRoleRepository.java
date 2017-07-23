package meng.xing.repository;

/**
 * Created by Administrator on 2017/7/17.
 */

import meng.xing.entity.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(exported = false) //不是rest风格的资源，不能通过url直接获取。必须从controller访问
public interface UserRoleRepository extends CrudRepository<UserRole,Long>{
    UserRole findByRole(String role);
}
