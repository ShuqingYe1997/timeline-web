package webapp.service;

import webapp.domain.User;
import webapp.domain.UserForm;
import webapp.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = ApplicationTest.class)
public class RegisterServiceUnitTest {

//    @Autowired
    private RegisterService registerService;

    private UserRepository userRepository = Mockito.mock(UserRepository.class);

    @Before
    public void setUp(){
        registerService = new RegisterService(userRepository);
    }

    @Test
    public void test_valid_username_with_identical_psws(){
        Mockito.when(userRepository.existsByUsername(anyString())).thenReturn(false);
        UserForm userForm = new UserForm("Mustang","BestPonyCar","BestPonyCar");
        assertFalse(registerService.isUsernameExisted(userForm.getUsername()));
        assertTrue(registerService.isPasswordEqual(userForm.getPassword(),userForm.getPassword2()));
    }

    @Test
    public void test_valid_username_with_different_psws(){
        Mockito.when(userRepository.existsByUsername(anyString())).thenReturn(false);
        UserForm userForm = new UserForm("Mustang","BestPonyCar","BestPonyCarEver");
        assertFalse(registerService.isUsernameExisted(userForm.getUsername()));
        assertFalse(registerService.isPasswordEqual(userForm.getPassword(),userForm.getPassword2()));
    }

    @Test
    public void test_invalid_username_with_identical_psws(){
        Mockito.when(userRepository.existsByUsername(anyString())).thenReturn(true);
        UserForm userForm = new UserForm("Mustang","BestPonyCar","BestPonyCar");
        assertTrue(registerService.isUsernameExisted(userForm.getUsername()));
        assertTrue(registerService.isPasswordEqual(userForm.getPassword(),userForm.getPassword2()));
    }

    @Test
    public void test_invalid_username_with_different_psws(){
        Mockito.when(userRepository.existsByUsername(anyString())).thenReturn(true);
        UserForm userForm = new UserForm("Mustang","BestPonyCar","BestPonyCarEver");
        assertTrue(registerService.isUsernameExisted(userForm.getUsername()));
        assertFalse(registerService.isPasswordEqual(userForm.getPassword(),userForm.getPassword2()));
    }

    @Test
    public void test_username_existed(){
        Mockito.when(userRepository.existsByUsername("Mustang")).thenReturn(true);
        assertTrue(registerService.isUsernameExisted("Mustang"));
    }

    @Test
    public void test_Username_NonExisted(){
        Mockito.when(userRepository.existsByUsername("Camaro")).thenReturn(false);
        assertFalse(registerService.isUsernameExisted("Camaro"));
    }

    @Test
    public void  test_password_identical(){
        String psw1 = "BestPonyCar";
        String psw2 = "BestPonyCar";
        assertTrue(registerService.isPasswordEqual(psw1,psw2));
    }

    @Test
    public void  test_password_different(){
        String psw1 = "BestPonyCar";
        String psw2 = "BestPonyCarEver";
        assertFalse(registerService.isPasswordEqual(psw1,psw2));
    }

    @Test
    public void test_add_new_user(){
        User user = new User("Mustang","BestPonyCar");
        Mockito.when(userRepository.save(user)).thenReturn(user);
        assertEquals("Mustang",registerService.add(user).getUsername());
    }
}