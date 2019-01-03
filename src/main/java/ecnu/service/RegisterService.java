package ecnu.service;

import ecnu.domain.User;
import ecnu.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterService {
    private final UserRepository userRepository;

    public RegisterService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    // illegal username: illegal length, illegal character, already exists
    @Transactional(readOnly = true)
    public boolean isUsernameLegal(String username){
        if(isLengthLegal(username) && !hasIllegalChar(username))
            return true;
        return false;
    }

    // illegal username: already exists
    @Transactional(readOnly = true)
    public boolean isUsernameExisted(String username){
        return userRepository.existsByUsername(username);
    }

    // illegal password:  illegal length, illegal character
    @Transactional(readOnly = true)
    public boolean isPasswordLegal(String psw){
        if(isLengthLegal(psw) && !hasIllegalChar(psw))
            return true;
        return false;
    }

    //2 passwords equal?
    @Transactional(readOnly = true)
    public boolean isPasswordEqual(String psw1,String psw2){
        if(psw1.equals(psw2))
            return true;
        return false;
    }

    @Transactional
    public void add(User user){
        userRepository.save(user);
    }

    private boolean isLengthLegal(String s){
        if (s.length() >= 6 && s.length() <= 20)
            return true;
        return false;
    }

    private boolean hasIllegalChar(String s){
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if( !isDigit(c) && !isLowerLetter(c) && !isUpperLetter(c))
                return true;
        }
        return false;
    }

    private boolean isDigit(char c){
        return (c<='9' && c>='0');
    }

    private boolean isLowerLetter(char c){
        return c<='z' && c>='a';
    }

    private boolean isUpperLetter(char c){
        return c<='Z' && c>='A';
    }

    private User findUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }

}
