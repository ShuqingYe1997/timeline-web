package webapp.repository;

import webapp.ApplicationTest;
import webapp.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationTest.class)
public class UserRepositoryTest {

    private UserRepository userRepository = Mockito.mock(UserRepository.class);
    private User user = new User("MaseratiSucks","Definitely");;


    @Test
    public void sanity(){
        assertNotNull(userRepository);
    }

    @Test
    public void test_user_exists(){
        Mockito.when(userRepository.existsByUsername("MaseratiSucks")).thenReturn(true);
        assertThat(userRepository.existsByUsername("MaseratiSucks"),is(true));
        assertThat(userRepository.existsByUsername("MaseratiWins"),is(false));

    }

    @Test
    public void test_user_Maserati(){
        Mockito.when(userRepository.findUserByUsername(anyString())).thenReturn(user);
        User sucks = userRepository.findUserByUsername("MaseratiSucks");
        assertNotNull(sucks);
        assertThat(sucks.getPassword()).isEqualTo("Definitely");
    }
}