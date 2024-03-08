package courseonline.com.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import courseonline.com.demo.enity.Cart;
import courseonline.com.demo.enity.Login;
import courseonline.com.demo.enity.ScrapedData;
import courseonline.com.demo.enity.User;
import courseonline.com.demo.respository.ScrapedDataReponsitory;
import courseonline.com.demo.service.CartService;
import courseonline.com.demo.service.LoginService;
import courseonline.com.demo.service.UserService;
import courseonline.com.demo.service.WebScrapingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class CartController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    @Autowired
    private ScrapedDataReponsitory scrapedDataReponsitory;

    @Autowired
    private WebScrapingService webScrapingService;

    @Autowired
    private CartService cartService;

    // @GetMapping("/Shopping/{loginID}")
    // public String cart(@PathVariable int loginID, @PathVariable int courseID, Model model) {
    //     Login login = loginService.getById(loginID);
    //     User user = userService.getUserByLoginId(loginID);
    //     List<ScrapedData> cart = cartService.getCourse(loginID);
    //     Optional<ScrapedData> course = scrapedDataReponsitory.findById(courseID);

    //     model.addAttribute("login", login);
    //     model.addAttribute("user", user);
    //     model.addAttribute("course", course);
    //     model.addAttribute("cart", cart);
    //     return "Shopping";
    // }
    @GetMapping("/Shopping/{loginID}")
    public String cart(@PathVariable int loginID, Model model) {
        Login login = loginService.getById(loginID);
        User user = userService.getUserByLoginId(loginID);
        List<ScrapedData> cart = cartService.getCourse(loginID);

        model.addAttribute("login", login);
        model.addAttribute("user", user);
        model.addAttribute("cart", cart);

        return "Shopping";
    }

    
    // @PostMapping("\"/Shopping/{loginID}/{courseID}\"")
    // public String AddCart(@PathVariable int loginID, @PathVariable int courseID, Model model, @RequestParam int count) {
    //     Login login = loginService.getById(loginID);
    //     User user = userService.getUserByLoginId(loginID);
    //     Optional<ScrapedData> course = scrapedDataReponsitory.findById(courseID);

    //     Cart cart = new Cart();
    //     cart.setUser(user);
    //     cart.getScrapedData(course);
    //     cart.setCount(count);
    //     cartService.saveCart(cart);

    //     // model.addAttribute(null, cart)

    //     return "Shopping";
    // }
    
}
