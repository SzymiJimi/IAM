package com.inzynieria.insurance.controller;


import com.inzynieria.insurance.dto.UserDto;
import com.inzynieria.insurance.model.User;
import com.inzynieria.insurance.repository.UserRepository;
import com.inzynieria.insurance.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;


@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @RequestMapping(value="/add", method = RequestMethod.POST)

    public String createUser(@RequestBody User user){

        LOGGER.info("Dodawanie informacji użytkownika do bazy");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "Zarejestrowano pomyślnie";
    }

    @RequestMapping(value="/update/{id}/{username}")
    public String updateUser(@PathVariable(value="id") Integer id, @PathVariable(value="username") String username) throws ValidationException {


        LOGGER.info("Update użytkownika w bazie o ID= "+id);
        User user= userRepository.findOne(id);
        UserDto userToUpdate= new UserDto();
        userToUpdate.setIdUser(user.getIdUser());
        userToUpdate.setUsername(username);
        userToUpdate.setName("Nowak");
        userToUpdate.setSurname("Malinowska");
        userToUpdate.setEmail("anna@wp.pl");
        userToUpdate.setRole("ROLE_ADMIN");
        userService.updateUser(userToUpdate, id);
        return "user/userUpdate";
    }
}
