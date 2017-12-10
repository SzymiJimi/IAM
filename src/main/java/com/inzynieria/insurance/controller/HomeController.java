package com.inzynieria.insurance.controller;


import com.inzynieria.insurance.commands.config.CommandsConfig;
import com.inzynieria.insurance.repository.CommandRepository;
import com.inzynieria.insurance.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;


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
    @RequestMapping(value="/")
    public ModelAndView start(final Principal principal, ModelMap model){
        if (null == principal)
        {
            return new ModelAndView("index");
        }
        return new ModelAndView("redirect:/home", model);
    }

    @RequestMapping(value="/accessDenied")
    public String accesDenied(){
        CommandsConfig.createCommands();
        return "accessDenied";
    }

        @RequestMapping(value="/login")
          public ModelAndView login(final Principal principal, ModelMap model){
            if (null != principal)
            {
                return new ModelAndView("redirect:/home", model);
            }
             return new ModelAndView("login/login");
            }



        @RequestMapping(value = "/find/findUser")
        public String findUser(){ return "find/findUser";}

        @PreAuthorize("hasRole('AGENT')")
        @RequestMapping(value = "/find/findClient")
        public ModelAndView findClient(){
            ModelAndView mav = new ModelAndView("find/findClient");
            return mav;
        }

         @PreAuthorize("hasRole('AGENT')")
         @RequestMapping(value = "/new/contract")
        public ModelAndView newContract(){
            ModelAndView mav = new ModelAndView("contract/new");
            return mav;
        }

        @RequestMapping(value = "/client/clientData")
        public String clientData(){ return "client/clientData";}

    @RequestMapping(value = "/find/findOffer")
    public String findOffer(){ return "find/findOffer";}

}
