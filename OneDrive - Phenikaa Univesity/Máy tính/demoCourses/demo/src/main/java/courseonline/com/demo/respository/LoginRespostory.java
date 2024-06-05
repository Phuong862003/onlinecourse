package courseonline.com.demo.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import courseonline.com.demo.enity.Login;

@Repository
public interface LoginRespostory extends JpaRepository<Login, Integer>{
    Login findByUsernameAndPassword(String username, String password);

    Login findByUsername(String username);

    Login findById(int id);
    
} 
