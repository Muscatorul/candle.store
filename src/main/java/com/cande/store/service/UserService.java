package com.cande.store.service;

import com.cande.store.dto.UserDto;
import com.cande.store.entity.User;
import com.cande.store.mapper.UserMapper;
import com.cande.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    public void saveUser(UserDto userDto){
        User user =userMapper.mapp(userDto);
        userRepository.save(user);

    }
}
