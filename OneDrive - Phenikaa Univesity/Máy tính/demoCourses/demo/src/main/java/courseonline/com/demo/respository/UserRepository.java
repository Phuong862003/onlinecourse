package courseonline.com.demo.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import courseonline.com.demo.enity.User;
import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    User findByEmail(String email);
    List<User> findByNameContaining(String name);

    Optional<User> findById(int id);

    User findByLoginId(int loginId);
    List<User> findAll();
    void deleteById(int id);
}
