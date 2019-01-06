package webapp.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "username cannot be empty")
    @Size(min=6, max=20,message = "wrong username and/or password")  //safety issue
    @Pattern(regexp="^[A-Za-z0-9]+$",message = "wrong username and/or password")
    private String username;

    @NotEmpty(message = "password cannot be empty")
    @Size(min=6, max=20,message = "wrong username and/or password")
    @Pattern(regexp="^[A-Za-z0-9]+$",message = "wrong username and/or password")
    private String password;

    public User() { }

    public User(String username , String password){
        this.username=username;
        this.password=password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
