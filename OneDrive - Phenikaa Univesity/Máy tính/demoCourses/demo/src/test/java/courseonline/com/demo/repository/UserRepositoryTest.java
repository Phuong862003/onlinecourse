package courseonline.com.demo.repository;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import courseonline.com.demo.enity.Login;
import courseonline.com.demo.enity.User;
import courseonline.com.demo.respository.LoginRespostory;
import courseonline.com.demo.respository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginRespostory loginRepostory;

    @Test
    @Rollback
    public void testCreateUser(){
        Login login = new Login();
        login.setUsername("testUser");
        login.setPassword("testPassword");
        loginRepostory.save(login);

        User user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setImage("john.jpg");
        user.setLogin(login);

        User savedUser = userRepository.save(user);

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testFindUserById() {
        int userId = 1;
        Optional<User> optionalUser = userRepository.findById(userId);

        assertThat(optionalUser).isPresent();
        User user = optionalUser.get();
        assertThat(user.getName()).isEqualTo("John Doe");
    }

    @Test
    @Rollback(false)
    public void testUpdateUser() {
        int userId = 1;
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();
        user.setEmail("new.email@example.com");

        userRepository.save(user);

        User updatedUser = userRepository.findById(userId).get();
        assertThat(updatedUser.getEmail()).isEqualTo("new.email@example.com");
    }

    @Test
    @Rollback(false)
    public void testDeleteUser() {
        int userId = 1;
        userRepository.deleteById(userId);

        Optional<User> optionalUser = userRepository.findById(userId);
        assertThat(optionalUser).isNotPresent();
    }
}
