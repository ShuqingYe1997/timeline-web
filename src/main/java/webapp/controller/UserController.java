package webapp.controller;

import webapp.domain.Post;
import webapp.domain.User;
import webapp.service.LoginService;
import webapp.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.*;


@Controller
@RequestMapping("demo")
@SessionAttributes(types={List.class, User.class},value = {"allPosts","user"})
public class UserController {

    private LoginService loginService;

    private PostService postService;

    UserController(LoginService loginService, PostService postService){
        this.loginService=loginService;
        this.postService = postService;
    }

    @GetMapping("")
    public String getUserId(Map<String, Object> model) {
        model.put("user",new User());   //同名属性
        return "signin";
    }

    @PostMapping("/login")
    public String userLogin(@Valid User user, BindingResult bindingResult, Map<String, Object> model) {
        // illegal chars or illegal length
        if (bindingResult.hasErrors()) {
            model.put("errors", bindingResult.getFieldError());
            return "errorPage_login";
        }
        else if(!loginService.isUserValid(user.getUsername(),user.getPassword())){
           model.put("errorMessage","wrong username and/or password");
            return "errorPage_login";
        }
        else{
            model.put("user",user);
            List<Post> allPosts = postService.getAllPosts();
            model.put("allPosts",allPosts);
            return "myTimeline";
        }
    }


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
