package com.inzynieria.insurance.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceView;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
    public class HomeController {

        @Autowired
        DataBaseController dataBaseController;

        @RequestMapping(value="/home")
        public void home(){
            dataBaseController.create();
        }

//    @RequestMapping(value="/home")
//    public View home(){
//        return new InternalResourceView("/resource/templates/home");
//    }
}
