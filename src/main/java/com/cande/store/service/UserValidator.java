package com.cande.store.service;

import com.cande.store.dto.UserDto;
import com.cande.store.entity.User;
import com.cande.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Optional;

@Service
public class UserValidator {
    @Autowired
    UserRepository userRepository;
    public void validate(UserDto userDto, BindingResult bindingResult){
        if (!userDto.getPassword().equals(userDto.getConfirmPassword())){
            FieldError fieldError = new FieldError("userDto", "confirmPassword", "Passwords don't match");
            bindingResult.addError(fieldError);
        }

        Optional <User> userOptional =  userRepository.findByEmail(userDto.getEmail());

        if(userOptional.isPresent()){
            FieldError fieldError = new FieldError("userDto", "email", "Email already exists");
            bindingResult.addError(fieldError);
        }
    }

}