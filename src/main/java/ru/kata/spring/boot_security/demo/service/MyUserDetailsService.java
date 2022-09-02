package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserServiceImpl userService;


    public MyUserDetailsService(UserServiceImpl userService) {
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userService.findUserName(name);
        if(user == null){
            throw new UsernameNotFoundException("user not found");
        }

         return user;
    }
}
