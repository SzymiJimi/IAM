package com.inzynieria.insurance.controller;

import com.inzynieria.insurance.model.User;
import com.inzynieria.insurance.repository.UserRepository;
import com.inzynieria.insurance.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping(value="/client")
public class ClientController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

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
    public User setClientData(@RequestBody Integer value) {
        User user = userRepository.findOne(value);
        return user;
    }


    @RequestMapping(value = "/show/{id}")
    public ModelAndView showUser(@PathVariable(value="id") Integer id)
    {
        ModelAndView mav = new ModelAndView("/client/clientData");
        User user  = userRepository.findOne(id);
        mav.addObject("name",user.getUsername());
        mav.addObject("name",user.getUsername());
        return mav;
    }


}
