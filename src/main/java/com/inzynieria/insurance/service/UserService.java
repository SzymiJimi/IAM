package com.inzynieria.insurance.service;


import com.inzynieria.insurance.dto.UserDto;
import com.inzynieria.insurance.model.User;
import org.springframework.boot.context.config.ResourceNotFoundException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.xml.bind.ValidationException;
import java.util.List;

/**
 * Interfejs zawierający 
 */
public interface UserService {

    //List<User> getUsers();

    /**
     * Aktualizowanie
     * @param user
     * @param id
     * @throws ValidationException
     */
    void updateUser(UserDto user, Integer id) throws ValidationException;

//    User findOrCreateUserByUsername(String username) throws ResourceNotFoundException;
//
//    User queryLdapUserAndSaveToDatabase(String username) throws ResourceNotFoundException;

    List<User> findUser(String name) throws ValidationException;

    List<UserDto> findClient(String data) throws ValidationException;

    UserDto findOneClient(Integer idUser) throws ValidationException;
}
