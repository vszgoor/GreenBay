package com.example.greenbay.security;

import com.example.greenbay.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//The actual (stored) user details can be accessed through UserDetailsService
@Service
public class MyUserDetailsService implements UserDetailsService {

  final
  UserRepository userRepository;

  public MyUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    var user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
    return new MyUserDetails(user);
  }
}
