package com.example.greenbay.services;

import com.example.greenbay.dtos.RegistrationDTO;
import com.example.greenbay.models.User;
import com.example.greenbay.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    final
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public RegistrationDTO save(RegistrationDTO registrationDTO) {
        User user = new User(registrationDTO.getUsername(), registrationDTO.getPassword());
        userRepository.save(user);
        return registrationDTO;
    }
}
