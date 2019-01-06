package webapp.controller;

import webapp.domain.User;
import webapp.domain.UserForm;
import webapp.service.RegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by smcsvip on 2018/12/25.
 */

@Controller
public class RegisterController {

    private RegisterService registerService;

    public RegisterController(RegisterService registerService){
        this.registerService = registerService;
    }

    @RequestMapping("/register")
    public String register(Model model){
        model.addAttribute("userForm",new UserForm());
        return "registerPage";
    }

    @PostMapping(value = "/submit")
    String userLogin(@Valid UserForm userForm, BindingResult bindingResult, ModelMap model) {

        String username = userForm.getUsername();
        String password = userForm.getPassword();
        String password2 = userForm.getPassword2();

        String message = "";

        // illegal chars or illegal length
        if (bindingResult.hasErrors()) {
            model.put("errors", bindingResult.getFieldError());
            return "errorPage_register";
        }
        else if (registerService.isUsernameExisted(username)) {
            message = "The username already exists.";
            model.addAttribute("errorMessage", message);
            return "errorPage_register";
        }
        else if (!registerService.isPasswordEqual(password2, password)) {
            message = "Inconsistent passwords!";
            model.addAttribute("errorMessage", message);
            return "errorPage_register";
        }
        else{
            User tmp = new User(username,password);
            registerService.add(tmp);
            model.addAttribute("user", tmp);
            model.addAttribute("name", username);
            model.addAttribute("password", password);
            return "signin";
        }
    }
}
