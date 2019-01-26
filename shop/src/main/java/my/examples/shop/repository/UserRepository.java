package my.examples.shop.repository;

import my.examples.shop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u inner join fetch u.roles where email = :email")
    public User getUserByEmail(@Param("email") String email);
}
