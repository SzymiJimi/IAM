package com.inzynieria.insurance.service;

import com.inzynieria.insurance.dto.UserDto;
import com.inzynieria.insurance.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserConv {

    public UserDto convertUserToUserDto(User user){
        return new UserDto(user.getIdUser(), user.getUsername(), "#",user.getName(), user.getSurname(), user.getEmail());
    }

    public List<UserDto> convertUserListToUserDtoList(List<User> users){

        List<UserDto> usersDto= new ArrayList<>();

        for (User user:users) {
            usersDto.add(new UserDto(user.getIdUser(), user.getUsername(), "#",user.getName(), user.getSurname(), user.getEmail()));
        }

        return usersDto;
    }

    public User convertUserDtoToUser(UserDto user){
        return new User(user.getIdUser(), user.getUsername(), "#",user.getName(), user.getSurname(), user.getEmail());
    }

}
