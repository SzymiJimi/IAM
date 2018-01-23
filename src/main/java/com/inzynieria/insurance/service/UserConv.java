package com.inzynieria.insurance.service;

import com.inzynieria.insurance.dto.UserDto;
import com.inzynieria.insurance.model.User;

public class UserConv {

    public UserDto convertUserToUserDto(User user){
        return new UserDto(user.getIdUser(), user.getUsername(), "#",user.getName(), user.getSurname(), user.getEmail());
    }

    public User convertUserDtoToUser(UserDto user){
        return new User(user.getIdUser(), user.getUsername(), "#",user.getName(), user.getSurname(), user.getEmail());
    }

}
