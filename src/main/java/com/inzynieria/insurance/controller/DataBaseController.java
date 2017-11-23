package com.inzynieria.insurance.controller;

import com.inzynieria.insurance.model.User;
import com.inzynieria.insurance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DataBaseController {

    @Autowired
    UserRepository userRepository;

    public void create()
    {
        System.out.println("HAAAAAAAAAAAAAALO: wyświetlam informacje");
        User user= new User();
        user.setUsername("krzychuj");
        user.setName("Krzychu");
        user.setSurname("Jarzyna");
        user.setEmail("jarzyna@wp.pl");
        user.setRole("USER");
        userRepository.save(user);
    }
}
