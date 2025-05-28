package com.example.Delivery.service;

import com.example.Delivery.model.User;
import com.example.Delivery.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class JwtUserDetailService implements UserDetailsService {

    @Autowired
    userRepository userRepository;


   @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
       User user = userRepository.findByUsername(username);
     System.out.println(user.getRole());
       return org.springframework.security.core.userdetails.User
               .withUsername(user.getUsername())
               .password(user.getPassword())
               .roles(user.getRole())
               .build();

   }
}
