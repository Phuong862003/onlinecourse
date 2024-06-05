package courseonline.com.demo.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import courseonline.com.demo.enity.ScrapedData;
import jakarta.transaction.Transactional;

@Repository
public interface ScrapedDataReponsitory extends JpaRepository<ScrapedData, Integer> {
    boolean existsByNameAndUrl(String name, String url);
    List<ScrapedData> findAll();
    ScrapedData findByNameAndUrl(String name, String url);
    List<ScrapedData> findByNameContaining(String name);
    Optional<ScrapedData> findById(int id);
    void deleteById(int id);
    @Transactional
    @Modifying
    @Query("UPDATE ScrapedData SET description = :description WHERE name = :name AND url = :url")
    void updateDescription(@Param("name") String name, @Param("url") String url, @Param("description") String description);



}
