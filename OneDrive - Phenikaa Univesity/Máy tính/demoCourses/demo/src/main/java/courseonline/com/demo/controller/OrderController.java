package courseonline.com.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import courseonline.com.demo.enity.Login;
import courseonline.com.demo.enity.Order;
import courseonline.com.demo.enity.ScrapedData;
import courseonline.com.demo.enity.User;
import courseonline.com.demo.respository.ScrapedDataReponsitory;
import courseonline.com.demo.service.LoginService;
import courseonline.com.demo.service.OrderService;
import courseonline.com.demo.service.UserService;
import courseonline.com.demo.service.WebScrapingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class OrderController {
    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private WebScrapingService webScrapingService;

    @Autowired
    private ScrapedDataReponsitory scrapedDataReponsitory;

    @Autowired
    private OrderService orderService;

    @GetMapping("/Pay_2/{loginID}/{courseID}")
    public String getOrder(@PathVariable int loginID, @PathVariable int courseID, Model model) {
        Login login = loginService.getById(loginID);
        User user = userService.getUserByLoginId(loginID);
        Optional<ScrapedData> course = scrapedDataReponsitory.findById(courseID);

        model.addAttribute("login", login);
        model.addAttribute("user", user);
        if(course.isPresent()){
            ScrapedData courses = course.get();
            if (courses.getDescription()==null || courses.getDescription().isEmpty()) {
                webScrapingService.scrapedCourseDetails(courses);
            }

            model.addAttribute("course", courses);

            return "Pay_2";
        }

         return "/";
    }

    @PostMapping("/Pay_2/{loginID}/{courseID}")
public String postOrder(@PathVariable int loginID, @PathVariable int courseID, Model model, @RequestParam int sdt, @RequestParam String diachi) {
    Login login = loginService.getById(loginID);
    User user = userService.getUserByLoginId(loginID);
    if (user == null) {
        // Xử lý trường hợp không tìm thấy người dùng
        return "redirect:/error";
    }
    Optional<ScrapedData> course = scrapedDataReponsitory.findById(courseID);
    if (course.isPresent()) {
        ScrapedData courses = course.get();
        if (courses.getDescription() == null || courses.getDescription().isEmpty()) {
            webScrapingService.scrapedCourseDetails(courses);
        }

        model.addAttribute("course", courses);
        Order order = new Order();
        order.setUser(user); // Đảm bảo user được thiết lập
        order.setScrapedData(courses);
        order.setTotal(courses.getPrice() - 10000);
        order.setSdt(sdt);
        order.setDiachi(diachi);
        orderService.saveOrder(order);

        return "redirect:/Pay_4/{loginID}/{courseID}";
    }
    return "redirect:/";
}

    
    @GetMapping("/Pay_4/{loginID}/{courseID}")
    public String getPay(@PathVariable int loginID, @PathVariable int courseID, Model model) {
        Login login = loginService.getById(loginID);
        User user = userService.getUserByLoginId(loginID);
        Optional<ScrapedData> course = scrapedDataReponsitory.findById(courseID);

        model.addAttribute("login", login);
        model.addAttribute("user", user);
        if(course.isPresent()){
            ScrapedData courses = course.get();
            if (courses.getDescription()==null || courses.getDescription().isEmpty()) {
                webScrapingService.scrapedCourseDetails(courses);
            }

            model.addAttribute("course", courses);

            return "Pay_4";
        }

         return "/";
    }
    
    // @PostMapping("/Pay_4/{loginID}/{courseID}")
    // public String showCourseDetail2(@PathVariable int loginID, @PathVariable int courseID, Model model) {
    //     Login login = loginService.getById(loginID);
    //     User user = userService.getUserByLoginId(loginID);
        
    //     model.addAttribute("login", login);
    //     model.addAttribute("user", user);

    //     Optional<ScrapedData> course = scrapedDataReponsitory.findById(courseID);
    //     if(course.isPresent()){
    //         ScrapedData courses = course.get();
    //         if (courses.getDescription()==null || courses.getDescription().isEmpty()) {
    //             webScrapingService.scrapedCourseDetails(courses);
    //         }

    //         model.addAttribute("course", courses);

    //         return "redirect:/Pay_2";
    //     } else {
    //         return "redirect:/";
    //     }
    // }
}
