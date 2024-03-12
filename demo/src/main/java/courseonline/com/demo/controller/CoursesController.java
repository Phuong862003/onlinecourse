package courseonline.com.demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import courseonline.com.demo.enity.Cart;
import courseonline.com.demo.enity.Login;
import courseonline.com.demo.enity.ScrapedData;
import courseonline.com.demo.enity.User;
import courseonline.com.demo.respository.ScrapedDataReponsitory;
import courseonline.com.demo.service.CartService;
import courseonline.com.demo.service.CoursesService;
import courseonline.com.demo.service.LoginService;
import courseonline.com.demo.service.SearchService;
import courseonline.com.demo.service.UserService;
import courseonline.com.demo.service.WebScrapingService;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class CoursesController {
    @Autowired
    private WebScrapingService webScrapingService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CoursesService coursesService;

    @Autowired
    private SearchService searchService;

    @Autowired
    private ScrapedDataReponsitory scrapedDataReponsitory;
    @GetMapping("/Course/{id}")
    public String showCourseDetail(@PathVariable("id") int id, Model model) {
        Optional<ScrapedData> course = scrapedDataReponsitory.findById(id);
        if(course.isPresent()){
            ScrapedData courses = course.get();
            if (courses.getDescription()==null||courses.getDescription().isEmpty()) {
                webScrapingService.scrapedCourseDetails(courses);
            }

            model.addAttribute("course", courses);
            return "Course";
        }else{
            return "redirect:/";
        }
    }

    @GetMapping("/Course2/{loginID}/{courseID}")
    public String showCourseDetail2(@PathVariable int loginID, @PathVariable int courseID, Model model) {
        Login login = loginService.getById(loginID);
        User user = userService.getUserByLoginId(loginID);
        
        model.addAttribute("login", login);
        model.addAttribute("user", user);

        Optional<ScrapedData> course = scrapedDataReponsitory.findById(courseID);
        if(course.isPresent()){
            ScrapedData courses = course.get();
            if (courses.getDescription()==null || courses.getDescription().isEmpty()) {
                webScrapingService.scrapedCourseDetails(courses);
            }

            model.addAttribute("course", courses);

            return "Course2";
        } else {
            return "redirect:/";
        }
    }


    @PostMapping("/Course2/{loginID}/{courseID}")
    public String AddCart(@PathVariable int loginID, @PathVariable int courseID, Model model, @RequestParam int count) {
        Login login = loginService.getById(loginID);
        User user = userService.getUserByLoginId(loginID);
        Optional<ScrapedData> course = scrapedDataReponsitory.findById(courseID);

        try {
            if (course.isPresent()) {
                Cart cart = new Cart();
                cart.setUser(user);
                cart.setScrapedData(course.get());
                cart.setCount(count);
                cartService.saveCart(cart);

                model.addAttribute("message", "Thêm giỏ hàng thành công");
            } else {
                model.addAttribute("error", "Không tìm thấy thông tin khóa học");
            }
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }

        return "redirect:/Course2/{loginID}/{courseID}";
    }

    @GetMapping("/ad-home")
    public String search_Course(@RequestParam(name="keyword", required = false) String keyword, Model model){
       
        List<ScrapedData> courses = searchService.search(keyword);
        model.addAttribute("course", courses);
        model.addAttribute("keyword", keyword != null ? keyword : "");
        return "ad-home";
    }

   
    
    @GetMapping("/course-home")
    public String course_home(Model model) {
        List<ScrapedData> courses = webScrapingService.scrapeCourses("https://gitiho.com/categories/tat-ca-khoa-hoc");
        model.addAttribute("courses", courses);
        return "course-home";
    }

    @GetMapping("/ad-home/new")
    public String createCourse(Model model){
        ScrapedData course = new ScrapedData();
        model.addAttribute("course", course);
        return "create_Course";
    }

    @PostMapping("/ad-home")
    public String saveCourse(@ModelAttribute("course") ScrapedData course) {
        coursesService.saveScrapedData(course);
        
        return "redirect:/course-home";
    }

    @GetMapping("/ad-home/edit/{id}")
    public String editCourse(@PathVariable int id, Model model){
        model.addAttribute("course",  coursesService.getCourseById(id));
        return "edit_course";
    }
    
    @PostMapping("/ad-home/{id}")
    public String updateCourse(@PathVariable int id,@ModelAttribute("course") ScrapedData course, Model model){
        ScrapedData exCourse = coursesService.getCourseById(id);
        exCourse.setId(id);
        exCourse.setImage(course.getImage());
        exCourse.setName(course.getName());
        exCourse.setPrice(course.getPrice());
        exCourse.setPriceOri(course.getPriceOri());
        exCourse.setTeacher(course.getTeacher());
        exCourse.setDescription(course.getDescription());

        coursesService.updateCourse(exCourse);
        return "redirect:/course-home";
    }

    @GetMapping("/ad-home/{id}")
    public String deleteCourse(@PathVariable int id){
        coursesService.delete(id);
        return "redirect:/course-home";
    }
    
}
