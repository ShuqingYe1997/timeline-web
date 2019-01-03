package ecnu.domain;

/**
 * Created by smcsvip on 2019/1/1.
 */
public class UserForm {
    String username;
    String password;
    String password2;

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
