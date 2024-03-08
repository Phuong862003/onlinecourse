package courseonline.com.demo.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import courseonline.com.demo.enity.Cart;
import courseonline.com.demo.enity.ScrapedData;
import courseonline.com.demo.enity.User;

import java.util.List;
import java.util.Optional;


@Repository
public interface CartReponsitory extends JpaRepository<Cart, Integer>{
    List<Cart> findByUser(User user);
    List<Cart> findByScrapedData(ScrapedData scrapedData);
    Optional<Cart> findById(int id);
    
} 