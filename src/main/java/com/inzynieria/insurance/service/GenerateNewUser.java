package com.inzynieria.insurance.service;

import com.inzynieria.insurance.controller.OrderController;
import com.inzynieria.insurance.controller.UserController;
import com.inzynieria.insurance.model.Orders;
import com.inzynieria.insurance.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Random;

@Service
public class GenerateNewUser {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    UserController userController;

    public void createNewUser(Orders orderData){
        String username=  orderData.getName().toLowerCase()+orderData.getIdOrder();
        RandomString randomString= new RandomString(10);
        String password= randomString.nextString();
        LOGGER.info("Wygenerowane hasło: "+password);
        User user = new User(username, password, orderData.getName(), orderData.getSurname(), orderData.getEmail());
        userController.createUser(user);
    }


}
