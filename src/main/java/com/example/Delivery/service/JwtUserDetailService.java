package com.example.Delivery.service;

import com.example.Delivery.model.User;
import com.example.Delivery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;


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
