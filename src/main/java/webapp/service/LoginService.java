package webapp.service;

import webapp.domain.User;
import webapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginService {
    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Transactional(readOnly = true)
    public Boolean isUserValid(String username,String password){
        User user = findUserByUsername(username);
        if(user == null)  // invalid username
            return false;
        else if(!password.equals(user.getPassword()))  //wrong psw
            return false;
        else
            return true;
    }

    public User findUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }
}
