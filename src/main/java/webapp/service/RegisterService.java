package webapp.service;

import webapp.domain.User;
import webapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterService {
    private final UserRepository userRepository;

    public RegisterService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    // illegal username: already exists
    @Transactional(readOnly = true)
    public boolean isUsernameExisted(String username){
        return userRepository.existsByUsername(username);
    }

    //2 passwords equal?
    @Transactional(readOnly = true)
    public boolean isPasswordEqual(String psw1,String psw2){
        if(psw1.equals(psw2))
            return true;
        return false;
    }

    @Transactional
    public User add(User user){
        return userRepository.save(user);
    }
}
