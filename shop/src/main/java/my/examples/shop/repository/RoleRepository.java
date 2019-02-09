package my.examples.shop.repository;

import my.examples.shop.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("select r from Role r where r.name = :name")
    public Role getRole(@Param("name") String name);

    @Query("select r from Role r where r.name = 'USER'")
    public Role getRoleIsUser();
}
