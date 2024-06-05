package courseonline.com.demo.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import courseonline.com.demo.service.LoginService;
import courseonline.com.demo.service.OrderService;
import courseonline.com.demo.service.UserService;
import courseonline.com.demo.service.WebScrapingService;
import courseonline.com.demo.respository.ScrapedDataReponsitory;
import courseonline.com.demo.enity.User;
import courseonline.com.demo.enity.ScrapedData;
import courseonline.com.demo.enity.Login;
import courseonline.com.demo.enity.Order;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

import java.util.Optional;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private LoginService loginService;

    @MockBean
    private WebScrapingService webScrapingService;

    @MockBean
    private ScrapedDataReponsitory scrapedDataReponsitory;

    @MockBean
    private OrderService orderService;

    @Test
    public void testGetOrder() throws Exception {
        Login login = new Login();
        login.setId(1);
        User user = new User();
        user.setId(1);
        user.setLogin(login);
        ScrapedData course = new ScrapedData();
        course.setId(1);

        when(loginService.getById(1)).thenReturn(login);
        when(userService.getUserByLoginId(1)).thenReturn(user);
        when(scrapedDataReponsitory.findById(1)).thenReturn(Optional.of(course));

        mockMvc.perform(get("/Pay_2/1/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("Pay_2"))
                .andExpect(model().attributeExists("login"))
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attributeExists("course"));
    }

    @Test
    public void testPostOrder() throws Exception {
        Login login = new Login();
        login.setId(1);
        User user = new User();
        user.setId(1);
        user.setLogin(login);
        ScrapedData course = new ScrapedData();
        course.setId(1);
        course.setPrice(50000);

        when(loginService.getById(1)).thenReturn(login);
        when(userService.getUserByLoginId(1)).thenReturn(user);
        when(scrapedDataReponsitory.findById(1)).thenReturn(Optional.of(course));

        mockMvc.perform(post("/Pay_2/1/1")
                .param("sdt", "123456789")
                .param("diachi", "123 ABC Street"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/Pay_4/1/1"));

        Order expectedOrder = new Order();
        expectedOrder.setUser(user);
        expectedOrder.setScrapedData(course);
        expectedOrder.setTotal(course.getPrice() - 10000);
        expectedOrder.setSdt(123456789);
        expectedOrder.setDiachi("123 ABC Street");

        // Xác minh rằng dịch vụ order đã lưu đúng đơn hàng
        // Mockito.verify(orderService).saveOrder(expectedOrder);
    }

    @Test
    public void testGetPay() throws Exception {
        Login login = new Login();
        login.setId(1);
        User user = new User();
        user.setId(1);
        user.setLogin(login);
        ScrapedData course = new ScrapedData();
        course.setId(1);

        when(loginService.getById(1)).thenReturn(login);
        when(userService.getUserByLoginId(1)).thenReturn(user);
        when(scrapedDataReponsitory.findById(1)).thenReturn(Optional.of(course));

        mockMvc.perform(get("/Pay_4/1/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("Pay_4"))
                .andExpect(model().attributeExists("login"))
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attributeExists("course"));
    }
}
