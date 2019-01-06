package webapp.domain;
import javax.validation.constraints.*;


/**
 * Created by smcsvip on 2019/1/1.
 */
public class UserForm {

    @NotEmpty(message = "username cannot be empty")
    @Size(min=6, max=20,message = "make sure your username is 6~20 characters long")
    @Pattern(regexp="^[A-Za-z0-9]+$",message = "make sure your username contains ONLY digits and letters")
    private String username;

    @NotEmpty(message = "password cannot be empty")
    @Size(min=6, max=20,message = "make sure your password is 6~20 characters long")
    @Pattern(regexp="^[A-Za-z0-9]+$",message = "make sure your password contains ONLY digits and letters")
    private String password;

    @NotEmpty(message = "confirm password cannot be empty")
    @Size(min=6, max=20,message = "make sure your password is 6~20 characters long")
    @Pattern(regexp="^[A-Za-z0-9]+$",message = "make sure your password contains ONLY digits and letters")
    private String password2;

    public UserForm(){}

    public UserForm(String username,String password,String password2){
        this.username=username;
        this.password=password;
        this.password2=password2;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
}
