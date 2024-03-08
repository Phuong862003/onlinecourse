package courseonline.com.demo.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import courseonline.com.demo.enity.Order;
import courseonline.com.demo.enity.ScrapedData;
import courseonline.com.demo.enity.User;

import java.util.List;
import java.util.Optional;


@Repository
public interface OrderReponsitory extends JpaRepository<Order, Integer> {
    List<Order> findByUser(User user);
    List<Order> findByScrapedData(ScrapedData scrapedData);
    Optional<Order> findById(int id);
    
} 