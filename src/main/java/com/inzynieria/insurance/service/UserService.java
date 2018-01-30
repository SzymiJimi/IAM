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
     * Metoda aktualizująca użytkownika
     * @param user Użytkownik w modelu uproszczonym
     * @param id identyfikator użytkownika
     * @throws ValidationException
     */
    void updateUser(UserDto user, Integer id) throws ValidationException;

//    User findOrCreateUserByUsername(String username) throws ResourceNotFoundException;
//
//    User queryLdapUserAndSaveToDatabase(String username) throws ResourceNotFoundException;

    /**
     * Metoda używana do wyszukiwania użytkownika
     * @param name nazwa parametru (nazwa lub imię lub nazwisko lub mail)
     * @return Lista użytkowników
     * @throws ValidationException
     */
    List<User> findUser(String name) throws ValidationException;

    /**
     * Metoda służąca do wyszukiwania klientów
     * @param data parametr wyszukiwania (nazwa, imię, nazwisko, e-mail)
     * @return Lista klientów
     * @throws ValidationException
     */
    List<UserDto> findClient(String data) throws ValidationException;

    /**
     * Metoda służąca do wyszukania klienta po numerze ID
     * @param idUser identyfikator użytkownika
     * @return Uproszczony model użytkownika
     * @throws ValidationException
     */
    UserDto findOneClient(Integer idUser) throws ValidationException;
}
