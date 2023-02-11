package com.example.greenbay.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

@Configuration
@EnableWebSecurity
public class SecurityConfigurer {

  private final OncePerRequestFilter jwtRequestFilter;

  private final MyUserDetailsService myUserDetailsService;

  public SecurityConfigurer(OncePerRequestFilter jwtRequestFilter,
                            MyUserDetailsService myUserDetailsService) {
    this.jwtRequestFilter = jwtRequestFilter;
    this.myUserDetailsService = myUserDetailsService;
  }

  //AuthenticationManager handles the authentication itself
  @Bean
  public AuthenticationManager authenticationManager(
      AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public AuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider impl = new DaoAuthenticationProvider();
    impl.setUserDetailsService(myUserDetailsService);
    impl.setPasswordEncoder(new BCryptPasswordEncoder());
    impl.setHideUserNotFoundExceptions(false);
    return impl;
  }

  //PasswordEncoder will hash the password
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  //Specifying how the endpoints should be authorized
  //permitAll() permit everybody to access that endpoint
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf().disable().authorizeRequests()
        // don't authenticate this particular request
        .antMatchers("/login").permitAll()
        .antMatchers("/registration").permitAll()
        .antMatchers("/error").permitAll()
        // all other requests need to be authenticated
        .anyRequest().authenticated()
        // make sure we use stateless session; session won't be used to store user's state.
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    // Add a filter to validate the tokens with every request
    http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }
}
