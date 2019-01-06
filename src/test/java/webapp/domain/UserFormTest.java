package webapp.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserFormTest {
    private UserForm userForm;
    @Test
    public void test_userForm(){
        userForm = new UserForm("xiaoming","111111","111111");
        assertThat(userForm.getPassword()).isEqualTo(userForm.getPassword2());
    }

}