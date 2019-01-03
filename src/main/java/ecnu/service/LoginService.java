package ecnu.service;

import ecnu.domain.User;
import ecnu.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginService {
    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository){
        this.userRepository=userRepository;
    }


    @Transactional(readOnly = true)
    public Boolean isUserAndPasswordNotNull(String username,String password){
        if(username.length()>0 && password.length()>0)
            return true;
        else
            return false;
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
