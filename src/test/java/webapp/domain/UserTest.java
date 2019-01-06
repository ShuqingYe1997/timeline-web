package webapp.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    private User user;

    @Before
    public void setUp() throws Exception {
        user = new User("myUsername","myPassword");
    }

    @Test
    public void why_bother_writing_this_method_to_test_username(){
        assertEquals("myUsername",user.getUsername());
    }

    @Test
   public void why_bother_writing_this_method_to_test_password(){
        assertEquals("myPassword",user.getPassword());
    }

    @Test
    public void why_bother_writing_this_method_to_test_id(){
        assertNull(user.getId());
    }
}