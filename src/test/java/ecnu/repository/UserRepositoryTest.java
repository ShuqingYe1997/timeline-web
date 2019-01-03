package ecnu.repository;

import ecnu.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
@Transactional
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        User user = new User("xiaohong","11111111");
        userRepository.save(user);
    }

    @Test
    public void testUserByUsernameXiaoming(){
        String username="xiaoming";
        User user=userRepository.findUserByUsername(username);
        assertEquals("12345678",user.getPassword());
    }

    @Test
    public void testUserByUsernameXiaohong(){
        String username="xiaohong";
        User user=userRepository.findUserByUsername(username);
        assertEquals("11111111",user.getPassword());
    }
}