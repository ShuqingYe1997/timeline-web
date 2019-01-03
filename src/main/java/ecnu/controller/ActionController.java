package ecnu.controller;

import ecnu.domain.Post;
import ecnu.domain.User;
import ecnu.domain.UserForm;
import ecnu.repository.UserRepository;
import ecnu.service.LoginService;
import ecnu.service.PostService;
import ecnu.service.RegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by smcsvip on 2018/12/25.
 */

@Controller
public class ActionController {

    private final LoginService loginService;
    private final RegisterService registerService;
    private final PostService postService;

    public ActionController(LoginService loginService, RegisterService registerService, PostService postService){
        this.loginService = loginService;
        this.registerService = registerService;
        this.postService = postService;
    }

    @RequestMapping("/register")
    public String register(Model model){
        model.addAttribute("userForm",new UserForm());
        return "register";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    String userLogin(UserForm userForm, Model model) {
//        System.out.println(user.getUsername());
//        System.out.println(user.getPassword());
        String username=userForm.getUsername();
        String password= userForm.getPassword();
        String password2=userForm.getPassword2();

        boolean verifyUsername = registerService.isUsernameLegal(userForm.getUsername());
        boolean verifyPassword = registerService.isPasswordLegal(userForm.getPassword());
        String message= "";

        if(!registerService.isPasswordEqual(password2,password)){
            message = "Two passwords are not equal.";
            model.addAttribute("errorMessage",message);
            return "errorPage_register";
        }
        else if(registerService.isUsernameExisted(username)){
            message = "Username already existed! Please change a name.";
            model.addAttribute("errorMessage",message);
            return "errorPage_register";
        }
        else if (verifyUsername && verifyPassword) {
            User tmp = new User();
            tmp.setUsername(username);
            tmp.setPassword(password);
            registerService.add(tmp);
            model.addAttribute("user",tmp);

            model.addAttribute("name", username);
            model.addAttribute("password", password);
            return "signin";
        }
        else {
            message = "Please make sure your username and password are 6~20 characters long, including ONLY letters and digits.";
            model.addAttribute("errorMessage",message);
            return "errorPage_register";
        }
    }

//
//    //todo: unfinished
//    @GetMapping("/logout")
//    public String clear(Model model){
//        model.addAttribute("user",new User());
//        return "signin";
//    }

}
