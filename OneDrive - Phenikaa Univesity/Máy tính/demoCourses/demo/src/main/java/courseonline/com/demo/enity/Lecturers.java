package courseonline.com.demo.enity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Lecturers")
public class Lecturers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecturerID")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name="sdt")
    private String sdt;
    @Column(name="email")
    private String email;
    @Column(name = "description")
    private String description;
    @JsonIgnore
    @OneToMany(mappedBy = "lecturers", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Courses> courses = new ArrayList<>();

    public Lecturers(){}
    public Lecturers(int id, String name, String sdt, String email, String description, List<Courses>courses){
        this.id=id;
        this.name=name;
        this.sdt=sdt;
        this.email=email;
        this.description=description;
        this.courses=courses;
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
    public String getSdt() {
        return sdt;
    }
    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<Courses> getCourses() {
        return courses;
    }
    public void setCourses(List<Courses> courses) {
        this.courses = courses;
    }
    
    

}
