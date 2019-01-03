package ecnu.controller;

import ecnu.domain.Post;
import ecnu.domain.User;
import ecnu.service.LoginService;
import ecnu.service.PostService;
import ecnu.service.RegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.*;


@Controller
@RequestMapping("demo")
@SessionAttributes(types={List.class,User.class},value = {"allPosts","user"})
public class UserController {
     private final LoginService loginService;
     private final PostService postService;

    UserController(LoginService loginService, PostService postService){
        this.loginService=loginService;
        this.postService = postService;
    }

    @GetMapping("/login")
    public String getUserId(Map<String, Object> model) {
        model.put("user",new User());   //同名属性
        return "signin";
    }

    @PostMapping("/login")
    public String printByuserId(@ModelAttribute User user,Map<String, Object> model) {
        String message = "";
        boolean notNull = loginService.isUserAndPasswordNotNull(user.getUsername(),user.getPassword());
        if(!notNull){
            message = "Username and/or password cannot be empty!";
            model.put("errorMessage",message);
            return "errorPage_login";

        }
        boolean verified = loginService.isUserValid(user.getUsername(),user.getPassword());
        if(verified){
            User tmp = loginService.findUserByUsername(user.getUsername());
            model.put("user",tmp);

            List<Post> allPosts = postService.getAllPosts();
            model.put("allPosts",allPosts);

            return "myTimeline";
        }
        else {
            message = "Wrong Username or password";
            model.put("errorMessage",message);
            return "errorPage_login";
        }
    }


    //todo: can be improve, 可以异步提高性能

    @RequestMapping("/refresh")
    String refresh(Map<String,Object>  model){
        List<Post> allPosts = (List<Post>)model.get("allPosts");

        Post post=postService.getNewPost();
        allPosts.add(0,post);

        model.put("allPosts",allPosts);
        postService.insertNewPost(post);

        return "myTimeline";
    }

    @RequestMapping("/logout")
    public String clear(SessionStatus status){

        status.setComplete();

        return "logoutPage";

    }
}
