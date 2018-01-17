package com.inzynieria.insurance.controller;

import com.inzynieria.insurance.model.Command;
import com.inzynieria.insurance.model.Role;
import com.inzynieria.insurance.repository.CommandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/command")
public class CommandController {

//    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
//
//    @Autowired
//    CommandRepository commandRepository;
//
//    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
//    public Command getRole(@PathVariable(value="id") Integer id)
//    {
//        return null;
//    }

}
