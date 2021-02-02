package com.server.EmailAuthentication.service;

import com.server.EmailAuthentication.domain.User;
import com.server.EmailAuthentication.dto.UserDto;
import com.server.EmailAuthentication.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User signup(UserDto userDto){
        // 만약 입력한 userName이 이미 있다면.. Exception
        if(userRepository.findByUserName(userDto.getUserName()).orElse(null) != null){
            throw new RuntimeException("이미 가입된 유저입니다.");
        }

        User user = User.builder()
                .userEmail(userDto.getUserEmail())
                .password(userDto.getPassword())
                .userName(userDto.getUserName())
                .build();

        return userRepository.save(user);
    }
}
