package com.inzynieria.insurance.service;

import com.inzynieria.insurance.dto.UserDto;
import com.inzynieria.insurance.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa konwertująca z klasy User na uproszczoną wersję modelu User
 */
public class UserConv {
    /**
     * Konwertująca metoda  z klasy User na uproszczoną wersję modelu User
     * @param user obiekt User
     * @return Uproszczona wersja modelu User
     */
    public UserDto convertUserToUserDto(User user){
        return new UserDto(user.getIdUser(), user.getUsername(), "#",user.getName(), user.getSurname(), user.getEmail());
    }

    /**
     * Metoda konswertująca listę użytkowników(encja User) do listy uproszczonego modelu User
     * @param users lista użytkowników (User)
     * @return Lista uproszczonego modelu User
     */
    public List<UserDto> convertUserListToUserDtoList(List<User> users){

        List<UserDto> usersDto= new ArrayList<>();

        for (User user:users) {
            usersDto.add(new UserDto(user.getIdUser(), user.getUsername(), "#",user.getName(), user.getSurname(), user.getEmail()));
        }

        return usersDto;
    }

    /**
     * Klasa konwertująca uproszczony model User do User
     * @param user obiekt uproszczonego modelu User
     * @return obiekt User
     */
    public User convertUserDtoToUser(UserDto user){
        return new User(user.getIdUser(), user.getUsername(), "#",user.getName(), user.getSurname(), user.getEmail());
    }

}
