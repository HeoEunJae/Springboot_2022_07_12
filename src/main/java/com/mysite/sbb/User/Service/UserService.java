package com.mysite.sbb.User.Service;

import com.mysite.sbb.User.dao.UserRepository;
import com.mysite.sbb.User.domain.User;
import com.mysite.sbb.uitl.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public User getUser(String username){
        Optional<User> user = this.userRepository.findByusername(username);
        if(user.isPresent()){
            return user.get();
        } else {
            throw new DataNotFoundException("유저 정보를 찾을 수 없습니다.");
        }
    }

}
