package webapp.controller;

import webapp.ApplicationTest;
import webapp.service.LoginService;
import webapp.service.PostService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = ApplicationTest.class)
public class LoginControllerTest {

    private String username = "F0rdMustang";
    private String password = "BestP0nyCar";
    private String suffix = "0123456789";

    @Autowired
    protected WebApplicationContext wac;

    private MockMvc mockMvc;

    @MockBean
    private LoginService loginService ;

    @MockBean
    private PostService postService ;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void test_goto_index_page() throws Exception{
        MvcResult result = mockMvc.perform(get("/demo"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"))
                .andReturn();
    }

    @Test
    public void test_happy_path_login() throws Exception{
        Mockito.when(loginService.isUserValid(anyString(),anyString())).thenReturn(true);
        Mockito.when(postService.getAllPosts()).thenReturn(new ArrayList<>());

        MvcResult result = mockMvc.perform(post("/demo/login")
                .param("username",username)
                .param("password",password))
                .andExpect(status().isOk())
                .andExpect(model().attributeDoesNotExist("errorMessage"))
                .andExpect(model().hasNoErrors())
                .andReturn();
        MockHttpSession session = (MockHttpSession) result.getRequest().getSession();
        assertNotNull(session.getAttribute("user"));
        assertNotNull(session.getAttribute("allPosts"));
    }

    @Test
    public void test_username_len_0() throws Exception{
        MvcResult result = mockMvc.perform(post("/demo/login")
                .param("username","")
                .param("password",password))
                .andExpect(status().isOk())
                .andExpect(model().errorCount(3))
                .andExpect(model().attributeDoesNotExist("errorMessage"))
                .andReturn();
    }

    @Test
    public void test_username_len_5() throws Exception{
        MvcResult result = mockMvc.perform(post("/demo/login")
                .param("username",username.substring(0,5))
                .param("password",password))
                .andExpect(status().isOk())
                .andExpect(model().errorCount(1))
                .andExpect(model().attributeDoesNotExist("errorMessage"))
                .andReturn();
    }

    @Test
    public void test_username_len_21() throws Exception{
        MvcResult result = mockMvc.perform(post("/demo/login")
                .param("username",username+suffix)
                .param("password",password))
                .andExpect(status().isOk())
                .andExpect(model().errorCount(1))
                .andExpect(model().attributeDoesNotExist("errorMessage"))
                .andReturn();
    }

    @Test
    public void test_password_len_0() throws Exception{
        MvcResult result = mockMvc.perform(post("/demo/login")
                .param("username",username)
                .param("password",""))
                .andExpect(status().isOk())
                .andExpect(model().errorCount(3))
                .andExpect(model().attributeDoesNotExist("errorMessage"))
                .andReturn();
    }

    @Test
    public void test_password_len_5() throws Exception{
        MvcResult result = mockMvc.perform(post("/demo/login")
                .param("username",username)
                .param("password",password.substring(0,5)))
                .andExpect(status().isOk())
                .andExpect(model().errorCount(1))
                .andExpect(model().attributeDoesNotExist("errorMessage"))
                .andReturn();
    }

    @Test
    public void test_password_len_21() throws Exception{
        MvcResult result = mockMvc.perform(post("/demo/login")
                .param("username",username)
                .param("password",password+suffix))
                .andExpect(status().isOk())
                .andExpect(model().errorCount(1))
                .andExpect(model().attributeDoesNotExist("errorMessage"))
                .andReturn();
    }

    @Test
    public void test_username_illegal() throws Exception{
        MvcResult result = mockMvc.perform(post("/demo/login")
                .param("username",username.replace('g','_'))
                .param("password",password))
                .andExpect(status().isOk())
                .andExpect(model().errorCount(1))
                .andExpect(model().attributeDoesNotExist("errorMessage"))
                .andReturn();

        verify(loginService,times(0)).isUserValid(anyString(),anyString());
    }

    @Test
    public void test_password_illegal() throws Exception{
        MvcResult result = mockMvc.perform(post("/demo/login")
                .param("username",username)
                .param("password",password.replace('r','_')))
                .andExpect(status().isOk())
                .andExpect(model().errorCount(1))
                .andExpect(model().attributeDoesNotExist("errorMessage"))
                .andReturn();

        verify(loginService,times(0)).isUserValid(anyString(),anyString());
    }

    @Test
    public void test_username_not_existed() throws Exception{
        Mockito.when(loginService.isUserValid(anyString(),anyString())).thenReturn(false);
        MvcResult result = mockMvc.perform(post("/demo/login")
                .param("username",username.replace('g','k'))
                .param("password",password))
                .andExpect(status().isOk())
                .andExpect(model().hasNoErrors())
                .andExpect(model().attributeExists("errorMessage"))
                .andReturn();
    }

    @Test
    public void test_username_password_dont_match() throws Exception{
        Mockito.when(loginService.isUserValid(anyString(),anyString())).thenReturn(false);
        MvcResult result = mockMvc.perform(post("/demo/login")
                .param("username",username)
                .param("password","USMuscleCar"))
                .andExpect(status().isOk())
                .andExpect(model().hasNoErrors())
                .andExpect(model().attributeExists("errorMessage"))
                .andReturn();
    }
}