package courseonline.com.demo.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import courseonline.com.demo.service.LoginService;
import courseonline.com.demo.service.UserService;
import courseonline.com.demo.enity.UserDto;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@WebMvcTest(RegisterController.class)
public class RegisterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private LoginService loginService;

    @Test
    public void testShowRegisterForm() throws Exception {
        mockMvc.perform(get("/signup"))
                .andExpect(status().isOk())
                .andExpect(view().name("signup"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    public void testRegisterSuccess() throws Exception {
        UserDto userDto = new UserDto("PP", "pp@gmail.com", "", "Password123", "customer");

        mockMvc.perform(post("/signup")
                .flashAttr("userDto", userDto))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/signup"));
    }

    @Test
    public void testRegisterFailure() throws Exception {
        UserDto userDto = new UserDto("John Doe", "john.doe@example.com", "john", "password", "customer");

        doThrow(new Exception("Email or username already exists.")).when(userService).register(userDto);

        mockMvc.perform(post("/signup")
                .flashAttr("userDto", userDto))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("/signup?error=Email*or*username*already*exists*"))
                .andExpect(flash().attribute("errorMessage", "Email or username already exists."));
    }
}

