package com.company;

import com.company.config.SecurityConfiguration;
import com.company.user.User;
import com.company.user.UserController;
import com.company.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(UserController.class)
@ContextConfiguration(classes = UserController.class)
public class UserContollerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void tesetGetUsers()
        throws Exception {
        User user = User.builder().username("Sulton 苏文通").build();
        User admin = User.builder().username("admin").build();

        List<User> allUsers = Arrays.asList(user, admin);

        when(userService.getUsers()).thenReturn(allUsers);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/daily-report"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Sulton 苏文通"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("admin"));
    }
}
