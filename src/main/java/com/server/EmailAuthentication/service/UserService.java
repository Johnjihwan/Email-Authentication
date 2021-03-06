package com.server.EmailAuthentication.service;

import com.server.EmailAuthentication.domain.User;
import com.server.EmailAuthentication.dto.UserDto;
import com.server.EmailAuthentication.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User signup(UserDto userDto){
        // 만약 입력한 userEmail이 이미 있다면.. Exception
        if(userRepository.findByUserEmail(userDto.getUserEmail()).orElse(null) != null){
            throw new RuntimeException("이미 가입된 유저입니다.");
        }

        User user = User.builder()
                .userEmail(userDto.getUserEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .userName(userDto.getUserName())
                .build();

        return userRepository.save(user);
    }

    @Transactional
    public void updateAuthStatus(String email) {
        User user = userRepository.findByUserEmail(email).orElseThrow();
        user.setAuthStatus(true);
    }
}
