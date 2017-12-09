package com.inzynieria.insurance.service;


import com.inzynieria.insurance.dto.UserDto;
import com.inzynieria.insurance.model.User;
import org.springframework.boot.context.config.ResourceNotFoundException;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface UserService {

    //List<User> getUsers();

    void updateUser(UserDto user, Integer id) throws ValidationException;

//    User findOrCreateUserByUsername(String username) throws ResourceNotFoundException;
//
//    User queryLdapUserAndSaveToDatabase(String username) throws ResourceNotFoundException;

    User getUserByName(String name) throws ValidationException;
}
