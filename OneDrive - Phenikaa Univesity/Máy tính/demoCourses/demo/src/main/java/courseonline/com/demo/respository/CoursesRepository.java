package courseonline.com.demo.respository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import courseonline.com.demo.enity.Courses;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Integer>{
    List<Courses> findByNameContaining(String name);
    Courses findByCategoriesId(int cateId);
    Courses findByLecturersId(int lecturerid);
    Optional<Courses> findById(int id);
    void deleteById(int id);    
} 