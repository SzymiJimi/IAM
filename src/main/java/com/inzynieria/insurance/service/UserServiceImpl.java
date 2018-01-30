package com.inzynieria.insurance.service;

import com.inzynieria.insurance.dto.RoleDto;
import com.inzynieria.insurance.dto.UserDto;
import com.inzynieria.insurance.model.Role;
import com.inzynieria.insurance.model.User;
import com.inzynieria.insurance.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Klasa serwisowa obsługująca użytkownika
 */
@Service
public class UserServiceImpl implements UserService {
    /**
     * Repozytorium użytkownika
     */
        @Autowired
        UserRepository userRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public void updateUser(UserDto userDto, Integer id) throws ValidationException {
        if (id == null) {
            throw new ValidationException("User ID cannot be null");
        }
        if (userDto == null) {
            throw new ValidationException("usersDto cannot be null");
        }
        User user = userRepository.findOne(id);
        List<Role> roleList = new ArrayList<>();
        List<RoleDto> dtoRoleList;
        dtoRoleList= userDto.getRoles();
        for (RoleDto roleDto : dtoRoleList) {
            roleList.add(new Role(roleDto.getIdRole(), roleDto.getName()));
        }

        user.setRoles(new HashSet<>(roleList));
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

    @Override
    public List<UserDto> findClient(String data ) throws ValidationException {
        if (data == null) {
            throw new ValidationException("The name cannot be null");
        }

        LOGGER.info("Przyjęte dane: <"+data+">");
        List<UserDto> users = new ArrayList<>();
        List<User> usersFromDb =  userRepository.findClient(data);
        LOGGER.info("Liczba znalezionych w bazie: "+usersFromDb.size());
        for (User user : usersFromDb) {
            users.add(new UserDto(user.getIdUser(), user.getUsername(), "#",user.getName(), user.getSurname(), user.getEmail()));
        }

        return users;
    }

    @Override
    public UserDto findOneClient(Integer idUser) throws ValidationException {
        if (idUser == null) {
            throw new ValidationException("The name cannot be null");
        }
        try{
            UserConv userConv = new UserConv();
            User userFromDb =  userRepository.findOneClient(idUser);
            UserDto userDto= userConv.convertUserToUserDto(userFromDb);
            return userDto;
        }catch(Exception e)
        {
            LOGGER.info("Nie można znaleźć użytkownika w bazie...");
        }

        return null;
    }

}
