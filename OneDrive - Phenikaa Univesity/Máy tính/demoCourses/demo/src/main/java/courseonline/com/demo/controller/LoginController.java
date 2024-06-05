package courseonline.com.demo.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import courseonline.com.demo.enity.Categories;
import courseonline.com.demo.enity.Courses;
import courseonline.com.demo.enity.Login;
import courseonline.com.demo.enity.ScrapedData;
import courseonline.com.demo.enity.User;
import courseonline.com.demo.enity.UserDto;
import courseonline.com.demo.respository.ScrapedDataReponsitory;
import courseonline.com.demo.service.LoginService;
import courseonline.com.demo.service.SearchService;
import courseonline.com.demo.service.UserService;
import courseonline.com.demo.service.WebScrapingService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {
    @Autowired
    private WebScrapingService webScrapingService;
    @Autowired
    private SearchService searchService;
    @Autowired
    private LoginService userService;

    @Autowired
    private UserService userService2;

    @Autowired
    private ScrapedDataReponsitory scrapedDataReponsitory;

    @GetMapping("/")
    public String home(Model model) {
        CompletableFuture<List<ScrapedData>> scrapingFuture = CompletableFuture.supplyAsync(() -> {
            return webScrapingService.scrapeCourses("https://gitiho.com/categories/tat-ca-khoa-hoc");
        });

        CompletableFuture<Void> processingFuture = scrapingFuture.thenAccept(courses -> {
            model.addAttribute("courses", courses);
        });

        // Đợi cả hai CompletableFuture hoàn thành
        CompletableFuture.allOf(scrapingFuture, processingFuture).join();

        return "home";
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("users", new User());
        mav.addObject("user", new Login());
        return mav;
    }
    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute("user") Login user, @ModelAttribute UserDto userDto,
                            @RequestParam(name = "action", required = false) String action, Model model) {

        ModelAndView modelAndView = new ModelAndView();

        if ("login".equals(action)) {
            // Handle login
            Login oauthUser = userService.login(user.getUsername(), user.getPassword());

            if (Objects.nonNull(oauthUser)) {
                if ("admin".equals(oauthUser.getRole()) || "ADMIN".equals(oauthUser.getRole())) {
                    modelAndView.setViewName("redirect:/ad-home");
                } else {
                    int userID = oauthUser.getId();
                    modelAndView.setViewName("redirect:/home2/" + userID);
                }
            } else {
                modelAndView.addObject("error", true);
                modelAndView.setViewName("login");
            }
        } 
        else {
            // Handle other cases or set default behavior
            modelAndView.setViewName("redirect:/login");
        }

        return modelAndView;
    }


    
    @GetMapping("/home2/{id}")
    public String showhome2(@PathVariable int id,  Model model) {
        Login login = userService.getById(id);
        // User user = userService2.getUserById(id);
        model.addAttribute("login", login);
        // model.addAttribute("user", user);
        // Optional<ScrapedData> course = scrapedDataReponsitory.findById(courseID);
        // model.addAttribute("course", course);
        CompletableFuture<List<ScrapedData>> scrapingFuture = CompletableFuture.supplyAsync(() -> {
            return webScrapingService.scrapeCourses("https://gitiho.com/categories/tat-ca-khoa-hoc");
        });

        CompletableFuture<Void> processingFuture = scrapingFuture.thenAccept(courses -> {
            model.addAttribute("courses", courses);
        });

        // Đợi cả hai CompletableFuture hoàn thành
        CompletableFuture.allOf(scrapingFuture, processingFuture).join();
        return "home2";
        
    }

    
}
