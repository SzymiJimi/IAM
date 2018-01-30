package com.inzynieria.insurance.controller;

import com.inzynieria.insurance.model.ClientData;
import com.inzynieria.insurance.model.Contract;
import com.inzynieria.insurance.repository.ClientDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/clientData")
public class ClientDataController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientDataController.class);

    @Autowired
    ClientDataRepository clientDataRepository;

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public String createClientData(@RequestBody ClientData clientData){

      LOGGER.info("Dodaje client data: "+clientData.toString());
        clientDataRepository.save(clientData);
        return "Zarejestrowano pomyślnie";
    }

}
