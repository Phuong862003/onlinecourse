package courseonline.com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import courseonline.com.demo.enity.Cart;
import courseonline.com.demo.enity.Order;
import courseonline.com.demo.enity.ScrapedData;
import courseonline.com.demo.enity.User;
import courseonline.com.demo.respository.OrderReponsitory;

@Service
public class OrderService {
    @Autowired
    private OrderReponsitory orderReponsitory;

    public void saveOrder(Order order){
        orderReponsitory.save(order);
    }


    public Order getOrderById(int id){
        return orderReponsitory.findById(id).orElse(null);
    }

    public List<Order> getOrderUser(User user){
        return orderReponsitory.findByUser(user);
    }

    public List<Order> getOrderScrap( ScrapedData scrapedData){
        return orderReponsitory.findByScrapedData(scrapedData);
    }

    public void saveCartUpdate(Order order){
        orderReponsitory.save(order);
    }
}
