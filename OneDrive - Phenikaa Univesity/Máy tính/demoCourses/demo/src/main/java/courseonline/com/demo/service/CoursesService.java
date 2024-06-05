package courseonline.com.demo.service;

import java.util.ArrayList;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import courseonline.com.demo.enity.ScrapedData;
import courseonline.com.demo.respository.ScrapedDataReponsitory;

@Service
public class CoursesService {
    @Autowired
    private ScrapedDataReponsitory scrapedDataReponsitory;

    public List<ScrapedData> listAll(){
        return scrapedDataReponsitory.findAll();
    }

    public ScrapedData saveScrapedData(ScrapedData scrapedData){
        return scrapedDataReponsitory.save(scrapedData);
    }

    public ScrapedData getCourseById(int id){
        return scrapedDataReponsitory.findById(id).get();
    }

    public void delete(int id){
        scrapedDataReponsitory.deleteById(id);
    }

    public ScrapedData updateCourse(ScrapedData scrapedData){
        return scrapedDataReponsitory.save(scrapedData);
    }

    public static boolean isNotBlank(String str) {
        return str != null && str.trim().length() > 0;
    }

    public List<ScrapedData> search(String keyword) {
        if (keyword != null && !keyword.isBlank()) {
            List<ScrapedData> courses = scrapedDataReponsitory.findByNameContaining(keyword);
            for (ScrapedData course : courses) {
                String imageUrl = course.getImage();
                if (isNotBlank(imageUrl)) {
                    course.setImage(processImageUrl(imageUrl));
                } else {
                    imageUrl = "https://znews-photo.zingcdn.me/w660/Uploaded/natmts/2023_02_03/z4080571344877_dcc05eb033d9e910039ad77df6eb1b05.jpg";
                }
                
                course.setImage(imageUrl);
            }
            return courses;
        } else {
            List<ScrapedData> courses = scrapedDataReponsitory.findAll();
            for (ScrapedData course : courses) {
                String imageUrl = course.getImage();
                if (isNotBlank(imageUrl)) {
                    course.setImage(processImageUrl(imageUrl));
                } else {
                    imageUrl = "https://znews-photo.zingcdn.me/w660/Uploaded/natmts/2023_02_03/z4080571344877_dcc05eb033d9e910039ad77df6eb1b05.jpg";
                }
                course.setImage(imageUrl);
            }
            return courses;
        }

    }

    private String processImageUrl(String imageUrl) {
        return null;
    }

    public ScrapedData getById(int id){
        return scrapedDataReponsitory.findById(id).get();
    }


    public List <ScrapedData> getAll(){
       List<ScrapedData> courses= scrapedDataReponsitory.findAll();
       for (ScrapedData course : courses) {
                String imageUrl = course.getImage();
                if (isNotBlank(imageUrl)) {
                    course.setImage(processImageUrl(imageUrl));
                } else {
                    imageUrl = "https://znews-photo.zingcdn.me/w660/Uploaded/natmts/2023_02_03/z4080571344877_dcc05eb033d9e910039ad77df6eb1b05.jpg";
                }
                course.setImage(imageUrl);
            }
            return courses;
    }

}
