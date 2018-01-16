package com.inzynieria.insurance.controller;

import com.inzynieria.insurance.dto.UserDto;
import com.inzynieria.insurance.model.User;
import com.inzynieria.insurance.repository.UserRepository;
import com.inzynieria.insurance.service.UserConv;
import com.inzynieria.insurance.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import javax.xml.bind.ValidationException;
import java.util.List;


@RestController
@RequestMapping(value="/client")
public class ClientController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserController userController;


    @RequestMapping(value="/showData")
    public String show()
    {
        LOGGER.info("Jestem tutaj");
        return "";
    }


    @RequestMapping(value = "/show")
    public UserDto setClientData(@RequestBody Integer value) {
        User user = userRepository.findOne(value);
        UserConv userConv = new UserConv();
        UserDto userDto=  userConv.convertUserToUserDto(user);
        return userDto;
    }


    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public ModelAndView showUser(@PathVariable(value="id") Integer id)
    {
        ModelAndView mav = new ModelAndView("/client/clientData");
        return mav;
    }

    @RequestMapping(value="/find")

    public List<UserDto> findClients(@RequestBody String value) throws ValidationException {
        LOGGER.info("Jestem tutaj, value: "+ value);
        List<UserDto> users= userService.findClient(value);
        return users;
    }

}
