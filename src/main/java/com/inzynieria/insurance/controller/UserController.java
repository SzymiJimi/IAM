package com.inzynieria.insurance.controller;


import com.inzynieria.insurance.dto.UserDto;
import com.inzynieria.insurance.model.User;
import com.inzynieria.insurance.repository.UserRepository;
import com.inzynieria.insurance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.ValidationException;


@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @RequestMapping(value="/add")
    public String createUser(){

        System.out.println("Dodawanie informacji użytkownika do bazy");
        User user= new User();
        user.setUsername("krzychuj");
        user.setName("Krzychu");
        user.setSurname("Jarzyna");
        user.setEmail("jarzyna@wp.pl");
        user.setRole("USER");
        userRepository.save(user);
        return "user/user";
    }

    @RequestMapping(value="/update/{id}/{username}")
    public String updateUser(@PathVariable(value="id") Integer id, @PathVariable(value="username") String username) throws ValidationException {


        System.out.println("Update użytkownika w bazie o ID= "+id);
        User user= userRepository.findOne(id);
        UserDto userToUpdate= new UserDto();
        userToUpdate.setIdUser(user.getIdUser());
        userToUpdate.setUsername(username);
        userToUpdate.setName("Nowak");
        userToUpdate.setSurname("Malinowska");
        userToUpdate.setEmail("anna@wp.pl");
        userToUpdate.setRole("ADMIN");
        userService.updateUser(userToUpdate, id);
        return "user/userAdd";
    }
}
