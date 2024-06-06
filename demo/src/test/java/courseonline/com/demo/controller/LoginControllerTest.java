package courseonline.com.demo.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import courseonline.com.demo.service.LoginService;
import courseonline.com.demo.service.UserService;
import courseonline.com.demo.service.WebScrapingService;
import courseonline.com.demo.service.SearchService;
import courseonline.com.demo.enity.Login;
import courseonline.com.demo.respository.ScrapedDataReponsitory;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;

@WebMvcTest(LoginController.class)
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginService loginService;

    @MockBean
    private UserService userService2;

    @MockBean
    private WebScrapingService webScrapingService;

    @MockBean
    private SearchService searchService;

    @MockBean
    private ScrapedDataReponsitory scrapedDataReponsitory;

    @Test
    public void testGetLogin() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("users"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    public void testPostLogin_Success() throws Exception {
        Login user = new Login();
        user.setUsername("testUser");
        user.setPassword("testPassword");
        user.setRole("user");

        when(loginService.login(anyString(), anyString())).thenReturn(user);

        mockMvc.perform(post("/login")
                .param("action", "login")
                .param("username", "testUser")
                .param("password", "testPassword"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/home2/" + user.getId()));
    }

    @Test
    public void testPostLogin_Failure() throws Exception {
        when(loginService.login(anyString(), anyString())).thenReturn(null);

        mockMvc.perform(post("/login")
                .param("action", "login")
                .param("username", "wrongUser")
                .param("password", "wrongPassword"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("error"));
    }
}
