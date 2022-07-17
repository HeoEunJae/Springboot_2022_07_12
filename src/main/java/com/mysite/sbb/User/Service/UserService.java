package com.mysite.sbb.User.Service;

import com.mysite.sbb.User.dao.UserRepository;
import com.mysite.sbb.User.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User create(String username, String password, String email){
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        // 패스워드 암호화
        user.setPassword(passwordEncoder.encode(password));
        this.userRepository.save(user);
        return user;
    }

}
