package com.inzynieria.insurance.controller;

import com.inzynieria.insurance.model.Contract;
import com.inzynieria.insurance.model.User;
import com.inzynieria.insurance.repository.ContractRepository;
import com.inzynieria.insurance.repository.OfferRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contract")
public class ContractController {


    private static final Logger LOGGER = LoggerFactory.getLogger(ContractController.class);


    @Autowired
    ContractRepository contractRepository;

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public String createContract(@RequestBody Contract contract){
       LOGGER.info("Dodaje kontrakt:  idOferty: "+contract.getIdOffer()+" Data ważności:"+ contract.getExpirationDate()+", data stworzenia: "+contract.getStartDate()+" idUsera: "+contract.getIdUser());
        contractRepository.save(contract);
        return "Zarejestrowano pomyślnie";
    }

}
