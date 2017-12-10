package com.inzynieria.insurance.service;

import com.inzynieria.insurance.dto.UserDto;
import com.inzynieria.insurance.model.User;
import com.inzynieria.insurance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

        @Autowired
    UserRepository userRepository;


    @Override
    public void updateUser(UserDto userDto, Integer id) throws ValidationException {
        if (id == null) {
            throw new ValidationException("User ID cannot be null");
        }
        if (userDto == null) {
            throw new ValidationException("usersDto cannot be null");
        }
        User user = userRepository.findOne(id);
        user.setRole(userDto.getRole());
        user.setPassword(userDto.getPassword());
        user.setName(userDto.getName());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        userRepository.save(user);
    }

    @Override
    public List<User> findUser(String value ) throws ValidationException {
        if (value == null) {
            throw new ValidationException("The name cannot be null");
        }
        return userRepository.findUserByNameOrSurnameOrUsernameOrEmail(value, value, value, value);
    }


}
