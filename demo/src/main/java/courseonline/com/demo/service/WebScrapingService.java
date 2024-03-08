package courseonline.com.demo.service;

import courseonline.com.demo.enity.ScrapedData;
import courseonline.com.demo.respository.ScrapedDataReponsitory;
import jakarta.transaction.Transactional;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class WebScrapingService {
    @Autowired
    private ScrapedDataReponsitory scrapedDataReponsitory;
    public List<ScrapedData> scrapeCourses(String url) {
        List<ScrapedData> courses = new ArrayList<>();

        try {
            Document document = Jsoup.connect(url).get();

            Elements courseElements = document.select("div.col-md-6.col-xl-4.my-3 a");

            for (Element courseElement : courseElements) {
                ScrapedData course = new ScrapedData();

                String title = courseElement.select("h3.cou-title").text();
                course.setName(title);

                String courseUrl = courseElement.attr("href");
                course.setUrl(courseUrl);

                String priceOri = courseElement.select("p.mb-0.origin-price.color_text2.line-through.mt-auto").text();
                course.setPriceOri(parsePrice(priceOri));

                String price = courseElement.select("p.f-18.font-weight-bold.color_label").text();
                course.setPrice(parsePrice(price));

                String img = courseElement.select("img.lazyload").attr("data-src");
                course.setImage(img);

                String teacher = courseElement.select("p.f-12.color_text2.mb-0").text();
                course.setTeacher(teacher);
                if (!isCourseExists(course)) {
                    
                    scrapedDataReponsitory.save(course);
                    System.out.println("Name: " + course.getName());
                    System.out.println("URL: " + course.getUrl());
                    System.out.println("Price: " + course.getPrice());
                    System.out.println("PriceOri: " + course.getPriceOri());
                    System.out.println("Image: " + course.getImage());
                    System.out.println("Teacher: " + course.getTeacher());
                }
                courses.add(course);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return scrapedDataReponsitory.findAll();
    }

    public void scrapedCourseDetails(ScrapedData course){
        List<ScrapedData> courses = new ArrayList<>();
        try {
            Document courseDocument = Jsoup.connect(course.getUrl()).get();
            String description = courseDocument.select(".item-pixcel.d-flex.my-3").text();
            System.out.println("Decription from web: " + description);
            course.setDescription(description);
            if (!description.isEmpty()) {
                course.setDescription(description);
    
                if (!isCourseExists(course)) {
                    scrapedDataReponsitory.save(course);
                    
                } else {
                    scrapedDataReponsitory.updateDescription(course.getName(), course.getUrl(), course.getDescription());
                }
                
            }
                
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    private float parsePrice(String priceText) {
        if (!priceText.trim().isEmpty() && priceText.matches(".*\\d.*")) {
            return Float.parseFloat(priceText.replaceAll("[^0-9.]", ""));
        } else {
            return 0.0f; 
        }
    }
    
    private boolean isCourseExists(ScrapedData course) {
        return scrapedDataReponsitory.existsByNameAndUrl(course.getName(), course.getUrl());
    }
    
    @Transactional
    public void updateDescription(String name, String url, String description) {
        scrapedDataReponsitory.updateDescription(name, url, description);
    }
}
