package courseonline.com.demo.enity;

import java.util.Optional;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartID")
    private int id;

    @Column(name = "count")
    private int count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courseID", referencedColumnName = "courseID")
    private ScrapedData scrapedData;

    public Cart(){}

    public Cart(int id, User user, ScrapedData scrapedData, int count){
        this.id=id;
        this.user=user;
        this.scrapedData=scrapedData;
        this.count=count;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ScrapedData getScrapedData(Optional<ScrapedData> course) {
        return scrapedData;
    }

    public void setScrapedData(ScrapedData scrapedData) {
        this.scrapedData = scrapedData;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ScrapedData getScrapedData() {
        return scrapedData;
    }

    
}
