package courseonline.com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import courseonline.com.demo.enity.Login;
import courseonline.com.demo.enity.User;
import courseonline.com.demo.service.LoginService;
import courseonline.com.demo.service.UserService;

@Controller
public class ProfileController {
    @Autowired
    private UserService userService;

    @Autowired 
    private LoginService loginService;

    @GetMapping("/personal/{id}")
    public String personal(@PathVariable int id, Model model){
        Login login = loginService.getById(id);
        User user = userService.getUserByLoginId(id);

        model.addAttribute("user", user);
        model.addAttribute("login", login);
        return "personal";
    }

}
