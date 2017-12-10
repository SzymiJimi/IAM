package com.inzynieria.insurance.controller;


import com.inzynieria.insurance.dto.UserDto;
import com.inzynieria.insurance.model.User;
import com.inzynieria.insurance.repository.UserRepository;
import com.inzynieria.insurance.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;


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
        userToUpdate.setPassword("haslo");
        userToUpdate.setName("Nowak");
        userToUpdate.setSurname("Malinowska");
        userToUpdate.setEmail("anna@wp.pl");
        userToUpdate.setRole("ROLE_ADMIN");
        userService.updateUser(userToUpdate, id);
        return "user/userUpdate";
    }



    @RequestMapping(value="/find")
    public List<User> findUser(@RequestBody String value) throws ValidationException {
        LOGGER.info("Wyszukiwanie użytkownika w bazie");
        List<User> users= userService.findUser(value);
        LOGGER.info("Ilosc znalezionych userów: "+ users.size());
        return users;
    }
}
