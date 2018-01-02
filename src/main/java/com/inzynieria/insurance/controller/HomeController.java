package com.inzynieria.insurance.controller;


import com.inzynieria.insurance.commands.config.CommandsConfig;
import com.inzynieria.insurance.repository.CommandRepository;
import com.inzynieria.insurance.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
    public class HomeController {

        private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

        @Autowired
        DataBaseController dataBaseController;

        @Autowired
         CommandRepository commandRepository;

        @Autowired
         RoleRepository roleRepository;

       @RequestMapping(value="/home")
        public String home(){
           CommandsConfig.createCommands();
           return "home";
        }

        @RequestMapping(value="/login")
          public String login(){
         return "login/login";
            }

        @RequestMapping(value = "/find/findUser")
        public String findUser(){ return "find/findUser";}

        @RequestMapping(value = "/find/findClient")
        public ModelAndView findClient(){
            ModelAndView mav = new ModelAndView("find/findClient");
            return mav;
        }

        @RequestMapping(value = "/client/clientData")
        public String clientData(){ return "client/clientData";}

}
