package com.inzynieria.insurance.controller;


import com.inzynieria.insurance.dto.UserDto;
import com.inzynieria.insurance.model.Application;
import com.inzynieria.insurance.model.User;
import com.inzynieria.insurance.repository.ApplicationRepository;
import com.inzynieria.insurance.repository.UserRepository;
import com.inzynieria.insurance.service.ApplicationService;
import com.inzynieria.insurance.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.bind.ValidationException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/application")
public class ApplicationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationController.class);

    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    ApplicationService applicationService;


    @RequestMapping(value="/add", method = RequestMethod.POST)
    public String createApplication(@RequestBody Application application){

        LOGGER.info("Dodawanie wniosku");
        applicationRepository.save(application);
        return "Dodano wniosek pomyślnie";
    }


    @RequestMapping(value="/find")
    public List <Application> findApplication () throws ValidationException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        String value="";
        List <String> roles= applicationRepository.roleForFind(currentPrincipalName);
        List <Application> applications= new ArrayList<>();
        List <Application> tmp;
        LOGGER.info(roles.get(1));
        for(String role: roles) {
            if ((role.compareTo("ROLE_CARSPECIALIST")) == 0) {
                value = "CAR";
                tmp = applicationRepository.findApplicationByType(value);
                applications.addAll(tmp);

            } else if ((role.compareTo("ROLE_HEALTHSPECIALIST")) == 0) {
                value = "HEALTH";
                tmp = applicationRepository.findApplicationByType(value);
                applications.addAll(tmp);
            }
            else if ((role.compareTo("ROLE_TRAVELSPECIALIST")) == 0) {
                value = "TRAVEL";
                tmp = applicationRepository.findApplicationByType(value);
                applications.addAll(tmp);
            }
        }
        //List <Application> applications= (applicationRepository.findApplicationByType(value));
        return applications;
    }

    @RequestMapping(value="/show/{id}", method = RequestMethod.GET)
    public Application findApplication(@PathVariable(value="id") Integer id)
    {
        LOGGER.info("Id pobrane: "+id);
        Application application= applicationRepository.findOne(id);
        return application;
    }
}
