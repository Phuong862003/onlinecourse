package courseonline.com.demo.controller;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import courseonline.com.demo.enity.Categories;
import courseonline.com.demo.enity.Courses;
import courseonline.com.demo.enity.Login;
import courseonline.com.demo.enity.ScrapedData;
import courseonline.com.demo.enity.User;
import courseonline.com.demo.enity.UserDto;
import courseonline.com.demo.service.LoginService;
import courseonline.com.demo.service.UserService;

@Controller
public class RegisterController {
    @Autowired
    private UserService userService;
    @Autowired
    private LoginService loginService;

    @GetMapping("/signup")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping(path = "/signup")
    public String register(@ModelAttribute UserDto userDto, RedirectAttributes redirectAttributes) {
        try {
            userService.register(userDto);
            return "redirect:/signup";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Email or username already exists.");
            return "redirect:/signup?error=" + URLEncoder.encode("Email or username already exists.", StandardCharsets.UTF_8);
        }

    }

    @GetMapping("/member-home")
    public String member_home(Model model) {
        List<User> user = userService.getAll();
        model.addAttribute("user", user);
        return "member-home";
    }



    @PostMapping("/member-home")
    public String saveMember(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        
        return "redirect:/member-home";
    }

    @GetMapping("/member-home/edit/{id}")
    public String editMember(@PathVariable int id, Model model){
        model.addAttribute("user",  userService.getUserById(id));
        return "edit_member";
    }
    
    @PostMapping("/member-home/{id}")
public String updateUser(@PathVariable int id, @ModelAttribute("user") User updatedUser, Model model) {
  
    User existingUser = userService.getUserById(id);

 
        existingUser.setImage(updatedUser.getImage());
        existingUser.setLogin(updatedUser.getLogin());
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());

       
        userService.updateUser(existingUser);
 
   

    return "redirect:/member-home";
}

    

    @GetMapping("/member-home/{id}")
    public String deleteMember(@PathVariable int id){
        userService.delete(id);
        return "redirect:/member-home";
    }
    




}
