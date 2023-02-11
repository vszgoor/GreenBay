package com.example.greenbay.controllers;

import com.example.greenbay.dtos.RegistrationDTO;
import com.example.greenbay.exceptions.SimpleErrorDTO;
import com.example.greenbay.security.*;
import com.example.greenbay.services.UserService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
public class AuthenticationController {

  private final AuthenticationManager authenticationManager;

  private final MyUserDetailsService userDetailsService;

  private final JwtUtil jwtUtil;

//  private final UserService userService;

  @Autowired
  public AuthenticationController(AuthenticationManager authenticationManager,
                                  MyUserDetailsService userDetailsService, JwtUtil jwtUtil) {
    this.authenticationManager = authenticationManager;
    this.userDetailsService = userDetailsService;
    this.jwtUtil = jwtUtil;

  }

//  @PostMapping("/registration")
//  public ResponseEntity registration(@Valid @RequestBody RegistrationDTO registrationDTO) {
//    return ResponseEntity.status(201).body(userService.save(registrationDTO));
//
//  }

  @PostMapping("/login")
  public ResponseEntity createAuthenticationToken(
      @Valid @RequestBody AuthenticationRequest authenticationRequest)
      throws UsernameNotFoundException, BadCredentialsException {

    try {
      Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              authenticationRequest.getUsername(),
              authenticationRequest.getPassword())
      );
    } catch (UsernameNotFoundException e) {
      return ResponseEntity.status(401).body(new SimpleErrorDTO(
          "Username '" + authenticationRequest.getUsername() + "' is not found"));
    } catch (BadCredentialsException e) {
      return ResponseEntity.status(401).body(new SimpleErrorDTO("Incorrect password"));
    }

    final MyUserDetails userDetails =
        (MyUserDetails) userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
    final String jwt = jwtUtil.generateToken(userDetails);

    return ResponseEntity.ok(new AuthenticationResponse(jwt));
  }
}
