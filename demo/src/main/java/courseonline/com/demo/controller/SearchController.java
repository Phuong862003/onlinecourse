package courseonline.com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import courseonline.com.demo.enity.Categories;
import courseonline.com.demo.enity.Courses;
import courseonline.com.demo.enity.Login;
import courseonline.com.demo.enity.ScrapedData;
import courseonline.com.demo.enity.User;
import courseonline.com.demo.enity.UserDto;
import courseonline.com.demo.service.LoginService;
import courseonline.com.demo.service.SearchService;
import courseonline.com.demo.service.UserService;
@Controller
public class SearchController {
    @Autowired 
    private SearchService searchService;

    @Autowired
    private LoginService loginService;

    @GetMapping("/search")
    public String search(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        List<ScrapedData> courses = searchService.search(keyword);
        model.addAttribute("course", courses);
        model.addAttribute("keyword", keyword != null ? keyword : "");
        return "search";
    }

    @GetMapping("/search2/{id}")
    public String search2(
        @PathVariable int id,
        @RequestParam(name = "keyword", required = false) String keyword,
        Model model
    ) {
        List<ScrapedData> courses = searchService.search(keyword);
        model.addAttribute("course", courses);
        model.addAttribute("keyword", keyword != null ? keyword : "");
        Login login = loginService.getById(id);
        model.addAttribute("login", login);
        return "search2";
    }
}
