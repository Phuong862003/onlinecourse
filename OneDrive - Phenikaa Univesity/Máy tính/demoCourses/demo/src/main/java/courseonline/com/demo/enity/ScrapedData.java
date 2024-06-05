package courseonline.com.demo.enity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Course")
public class ScrapedData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courseID")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private String status;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private float price;

    @Column(name = "priceOri")
    private Float priceOri;

    @Column(name = "image")
    private String image;

    @Column(name = "teacher")
    private String teacher;

    @Column(name = "url")
    private String url;

    public ScrapedData(){}
    
    public ScrapedData(int id, String name, String status, String description, float price, String image, String url, Float priceOri, String teacher){
        this.id = id;
        this.name = name;
        this.status = status;
        this.description = description;
        this.price = price;
        this.image = image;
        this.url = url;
        this.priceOri = priceOri;
        this.teacher = teacher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Float getPriceOri() {
        return priceOri;
    }

    public void setPriceOri(Float priceOri) {
        this.priceOri = priceOri;
    }

    

}
