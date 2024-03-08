package courseonline.com.demo.enity;

import java.util.Date;

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
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="orderID")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courseID", referencedColumnName = "courseID")
    private ScrapedData scrapedData;

    @Column(name = "total")
    private float total;

    @Column(name="orderDate")
    private Date datetime;

    @Column(name="status")
    private String status;

    @Column(name="sdt")
    private int sdt;

    @Column(name = "diachi")
    private String diachi;

    public Order(){}

    public Order(int id, User user, ScrapedData scrapedData, float total, Date datetime, String status, int sdt, String diachi ){
        this.id=id;
        this.user=user;
        this.scrapedData=scrapedData;
        this.total=total;
        this.datetime=datetime;
        this.status=status;
        this.sdt = sdt;
        this.diachi=diachi;
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

    public ScrapedData getScrapedData() {
        return scrapedData;
    }

    public void setScrapedData(ScrapedData scrapedData) {
        this.scrapedData = scrapedData;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSdt() {
        return sdt;
    }

    public void setSdt(int sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    
}
