package webapp.service;

import webapp.domain.User;
import webapp.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(SpringJUnit4ClassRunner.class)
public class LoginServiceUnitTest {

    private LoginService loginService;

    private UserRepository userRepository = Mockito.mock(UserRepository.class);
    private User user = new User("Mustang","BestPonyCar");

    @Before
    public void setUp(){
        loginService = new LoginService(userRepository);
    }

    @Test
    public void test_valid_username_and_valid_psw(){
        Mockito.when(userRepository.findUserByUsername("Mustang")).thenReturn(user);
        assertTrue(loginService.isUserValid("Mustang","BestPonyCar"));
        //not invoked
//        Mockito.verify(userRepository).findUserByUsername("xiaoming");
    }

    @Test
    public void test_valid_username_and_wrong_psw(){
        Mockito.when(userRepository.findUserByUsername("Mustang")).thenReturn(user);
        assertFalse(loginService.isUserValid("Mustang","WorstPonyCar"));
    }

    @Test
    public void test_invalid_username(){
        Mockito.when(userRepository.findUserByUsername("Camaro")).thenReturn(null);
        assertFalse(loginService.isUserValid("Mustang","BestPonyCar"));
    }

    @Test
    public void test_find_user_by_name(){
        Mockito.when(userRepository.findUserByUsername(anyString())).thenReturn(user);
        assertEquals(loginService.findUserByUsername("Mustang"),user);
    }
}