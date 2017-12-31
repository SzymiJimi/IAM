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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
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
    public List <Application> findApplication (@RequestBody String value) throws ValidationException {
        List<Application> applications= applicationRepository.findApplicationByType(value);
        return applications;
    }
}
