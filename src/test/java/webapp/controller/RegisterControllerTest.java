package webapp.controller;

import webapp.ApplicationTest;
import webapp.domain.User;
import webapp.service.RegisterService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MvcResult;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = ApplicationTest.class)
public class RegisterControllerTest {

    private String username="F0rdMustang";
    private String password="BestP0nyCar";
    private String password2="BestP0nyCar";
    private String suffix="0123456789";

    @Autowired
    protected WebApplicationContext wac;

    private MockMvc mockMvc;

    @MockBean
    private RegisterService registerService ;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void test_goto_register_page() throws Exception{
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk());

        MvcResult result = mockMvc.perform(get("/register"))//执行请求
                .andReturn(); //返回MvcResult
        Assert.assertNotNull(result.getModelAndView().getModel().get("userForm")); //自定义断言
    }

    @Test
    public void test_happy_path_register() throws Exception{
        Mockito.when(registerService.isUsernameExisted(Mockito.any())).thenReturn(false);
        Mockito.when(registerService.isPasswordEqual(anyString(), anyString())).thenReturn(true);
        Mockito.when(registerService.add(any())).thenReturn(new User());

        MvcResult result = mockMvc.perform(post("/submit")
                .param("username",username)
                .param("password",password)
                .param("password2",password2))
                .andExpect(status().isOk())
                .andExpect(model().hasNoErrors())
                .andExpect(model().attributeDoesNotExist("errorMessage"))
                .andExpect(model().attributeExists("user"))
                .andReturn();
    }

    @Test
    public void test_username_len_0() throws Exception{
        Mockito.when(registerService.isUsernameExisted(Mockito.any())).thenReturn(false);
        Mockito.when(registerService.isPasswordEqual(anyString(), anyString())).thenReturn(true);

        MvcResult result = mockMvc.perform(post("/submit")
                .param("username","")
                .param("password",password)
                .param("password2",password2))
                .andExpect(status().isOk())
                .andExpect(model().attributeDoesNotExist("user"))
                .andExpect(model().hasErrors())
                .andExpect(model().errorCount(3))
                .andReturn();
    }

    @Test
    public void test_username_len_5() throws Exception{
        Mockito.when(registerService.isUsernameExisted(Mockito.any())).thenReturn(false);
        Mockito.when(registerService.isPasswordEqual(anyString(), anyString())).thenReturn(true);

        MvcResult result = mockMvc.perform(post("/submit")
                .param("username",username.substring(0,5))
                .param("password",password)
                .param("password2",password2))
                .andExpect(status().isOk())
                .andExpect(model().attributeDoesNotExist("user"))
                .andExpect(model().hasErrors())
                .andExpect(model().errorCount(1))
                .andReturn();
    }

    @Test
    public void test_username_len_21() throws Exception{
        Mockito.when(registerService.isUsernameExisted(Mockito.any())).thenReturn(false);
        Mockito.when(registerService.isPasswordEqual(anyString(), anyString())).thenReturn(true);

        MvcResult result = mockMvc.perform(post("/submit")
                .param("username",username+suffix)
                .param("password",password)
                .param("password2",password2))
                .andExpect(status().isOk())
                .andExpect(model().attributeDoesNotExist("user"))
                .andExpect(model().hasErrors())
                .andExpect(model().errorCount(1))
                .andReturn();
    }

    @Test
    public void test_password_len_0() throws Exception{
        Mockito.when(registerService.isUsernameExisted(Mockito.any())).thenReturn(false);
        Mockito.when(registerService.isPasswordEqual(anyString(), anyString())).thenReturn(true);

        MvcResult result = mockMvc.perform(post("/submit")
                .param("username",username)
                .param("password","")
                .param("password2",password2))
                .andExpect(status().isOk())
                .andExpect(model().attributeDoesNotExist("user"))
                .andExpect(model().hasErrors())
                .andExpect(model().errorCount(3))
                .andReturn();
    }
    @Test
    public void test_password_len_5() throws Exception{
        Mockito.when(registerService.isUsernameExisted(Mockito.any())).thenReturn(false);
        Mockito.when(registerService.isPasswordEqual(anyString(), anyString())).thenReturn(true);

        MvcResult result = mockMvc.perform(post("/submit")
                .param("username",username)
                .param("password",password.substring(0,5))
                .param("password2",password2))
                .andExpect(status().isOk())
                .andExpect(model().attributeDoesNotExist("user"))
                .andExpect(model().hasErrors())
                .andExpect(model().errorCount(1))
                .andReturn();
    }
    @Test
    public void test_password_len_21() throws Exception{
        Mockito.when(registerService.isUsernameExisted(Mockito.any())).thenReturn(false);
        Mockito.when(registerService.isPasswordEqual(anyString(), anyString())).thenReturn(true);

        MvcResult result = mockMvc.perform(post("/submit")
                .param("username",username)
                .param("password",password+suffix)
                .param("password2",password2))
                .andExpect(status().isOk())
                .andExpect(model().attributeDoesNotExist("user"))
                .andExpect(model().hasErrors())
                .andExpect(model().errorCount(1))
                .andReturn();
    }
    @Test
    public void test_password2_len_0() throws Exception{
        Mockito.when(registerService.isUsernameExisted(Mockito.any())).thenReturn(false);
        Mockito.when(registerService.isPasswordEqual(anyString(), anyString())).thenReturn(true);

        MvcResult result = mockMvc.perform(post("/submit")
                .param("username",username)
                .param("password",password)
                .param("password2",""))
                .andExpect(status().isOk())
                .andExpect(model().attributeDoesNotExist("user"))
                .andExpect(model().hasErrors())
                .andExpect(model().errorCount(3))
                .andReturn();
    }
    @Test
    public void test_password2_len_5() throws Exception{
        Mockito.when(registerService.isUsernameExisted(Mockito.any())).thenReturn(false);
        Mockito.when(registerService.isPasswordEqual(anyString(), anyString())).thenReturn(true);

        MvcResult result = mockMvc.perform(post("/submit")
                .param("username",username)
                .param("password",password)
                .param("password2",password2.substring(0,5)))
                .andExpect(status().isOk())
                .andExpect(model().attributeDoesNotExist("user"))
                .andExpect(model().hasErrors())
                .andExpect(model().errorCount(1))
                .andReturn();
    }
    @Test
    public void test_username_illegal() throws Exception{
        Mockito.when(registerService.isUsernameExisted(Mockito.any())).thenReturn(false);
        Mockito.when(registerService.isPasswordEqual(anyString(), anyString())).thenReturn(true);

        MvcResult result = mockMvc.perform(post("/submit")
                .param("username",username.replace('g','_'))
                .param("password",password)
                .param("password2",password2))
                .andExpect(status().isOk())
                .andExpect(model().attributeDoesNotExist("user"))
                .andExpect(model().hasErrors())
                .andExpect(model().errorCount(1))
                .andReturn();
    }

    @Test
    public void test_password_illegal() throws Exception{
        Mockito.when(registerService.isUsernameExisted(Mockito.any())).thenReturn(false);
        Mockito.when(registerService.isPasswordEqual(anyString(), anyString())).thenReturn(true);

        MvcResult result = mockMvc.perform(post("/submit")
                .param("username",username)
                .param("password",password.replace('r','_'))
                .param("password2",password2))
                .andExpect(status().isOk())
                .andExpect(model().attributeDoesNotExist("user"))
                .andExpect(model().hasErrors())
                .andExpect(model().errorCount(1))
                .andReturn();
    }

    @Test
    public void test_password2_illegal() throws Exception{
        Mockito.when(registerService.isUsernameExisted(Mockito.any())).thenReturn(false);
        Mockito.when(registerService.isPasswordEqual(anyString(), anyString())).thenReturn(true);

        MvcResult result = mockMvc.perform(post("/submit")
                .param("username",username)
                .param("password",password)
                .param("password2",password2.replace('r','_')))
                .andExpect(status().isOk())
                .andExpect(model().attributeDoesNotExist("user"))
                .andExpect(model().hasErrors())
                .andExpect(model().errorCount(1))
                .andReturn();
    }

    @Test
    public void test_password_inconsistent() throws Exception{
        Mockito.when(registerService.isUsernameExisted(Mockito.any())).thenReturn(false);
        Mockito.when(registerService.isPasswordEqual(anyString(), anyString())).thenReturn(false);

        MvcResult result = mockMvc.perform(post("/submit")
                .param("username",username)
                .param("password",password)
                .param("password2",password2.replace('0','o')))
                .andExpect(status().isOk())
                .andExpect(model().attributeDoesNotExist("user"))
                .andExpect(model().hasNoErrors())
                .andExpect(model().attributeExists("errorMessage"))
                .andReturn();
    }

    @Test
    public void test_existed_username() throws Exception{
        Mockito.when(registerService.isUsernameExisted(Mockito.any())).thenReturn(true);
        Mockito.when(registerService.isPasswordEqual(anyString(), anyString())).thenReturn(true);

        MvcResult result = mockMvc.perform(post("/submit")
                .param("username",username)
                .param("password",password)
                .param("password2",password2))
                .andExpect(status().isOk())
                .andExpect(model().attributeDoesNotExist("user"))
                .andExpect(model().hasNoErrors())
                .andExpect(model().attributeExists("errorMessage"))
                .andReturn();
    }
}