package com.inzynieria.insurance.controller;

import com.inzynieria.insurance.model.ClientData;
import com.inzynieria.insurance.model.Contract;
import com.inzynieria.insurance.repository.ClientDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/clientData")
public class ClientDataController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientDataController.class);

    @Autowired
    ClientDataRepository clientDataRepository;

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public String createClientData(@RequestBody ClientData clientData){
        LOGGER.info("Jestem tutaj ");
//        LOGGER.info("Dodaje kontrakt:  idOferty: "+clientData.getIdOffer()+" Data ważności:"+ contract.getExpirationDate()+", data stworzenia: "+contract.getStartDate()+" idUsera: "+contract.getIdUser());
        LOGGER.info("Dodaje client data: "+clientData.toString());
        clientDataRepository.save(clientData);
        return "Zarejestrowano pomyślnie";
    }

    @RequestMapping(value="/find/{id}", method = RequestMethod.GET)
    public ClientData findClientData(@PathVariable(value="id") Integer id){
        LOGGER.info("Przyjąłem id: "+id);
        ClientData clientData= clientDataRepository.findClientDataByUserId(id);
        return clientData;
    }

}
