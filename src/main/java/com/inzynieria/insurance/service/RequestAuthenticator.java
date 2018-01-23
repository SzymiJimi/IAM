package com.inzynieria.insurance.service;

import com.inzynieria.insurance.model.User;
import com.inzynieria.insurance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


public class RequestAuthenticator {

    @Autowired
    UserRepository userRepository;

    public User getLoggedUser(String name){
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String name= auth.getName();
        return userRepository.findByUsername(name);
    }

    public boolean authenticateRequest(){

        return true;
    }

}
