package com.example.greenbay.configurations;

import com.example.greenbay.models.User;
import com.example.greenbay.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class InitConfig implements CommandLineRunner {

  final
  UserRepository userRepository;

  public InitConfig(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    if(userRepository.count() == 0) {
      userRepository.save(new User("user1",new BCryptPasswordEncoder().encode("Pass123"),1000.0));
      userRepository.save(new User("user2",new BCryptPasswordEncoder().encode("Pass123"),1000.0));
      userRepository.save(new User("user3",new BCryptPasswordEncoder().encode("Pass123"),1000.0));
    }
   }
}
