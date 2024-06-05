package courseonline.com.demo.service;

import java.util.List;
import java.util.Optional;

import org.apache.regexp.recompile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import courseonline.com.demo.enity.Cart;
import courseonline.com.demo.enity.ScrapedData;
import courseonline.com.demo.enity.User;
import courseonline.com.demo.respository.CartReponsitory;
import courseonline.com.demo.respository.ScrapedDataReponsitory;
import courseonline.com.demo.respository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class CartService {
    @Autowired
    private CartReponsitory cartReponsitory;


    public void saveCart(Cart cart){
        cartReponsitory.save(cart);
    }


    public Cart getCartById(int id){
        return cartReponsitory.findById(id).orElse(null);
    }

    public List<Cart> getCartUser(User user){
        return cartReponsitory.findByUser(user);
    }

    public List<Cart> getCartScrap( ScrapedData scrapedData){
        return cartReponsitory.findByScrapedData(scrapedData);
    }

    public void saveCartUpdate(Cart cart){
        cartReponsitory.save(cart);
    }
    
    @PersistenceContext
    private EntityManager entityManager;

    public List<ScrapedData> getCourse(int loginId){
        String queryString = "SELECT c FROM Cart cart " +
                          "JOIN ScrapedData c ON cart.scrapedData.id = c.id " +
                          "WHERE cart.user.login.id = :loginId";
        System.out.println("UserId: " + loginId);
        System.out.println("Query: " + queryString);
                                
        List<ScrapedData> resultList = entityManager.createQuery(queryString, ScrapedData.class)
                                                            .setParameter("loginId", loginId)
                                                            .getResultList();
                                
        System.out.println("Result List: " + resultList);
                                
        return resultList;
    }
    
}
