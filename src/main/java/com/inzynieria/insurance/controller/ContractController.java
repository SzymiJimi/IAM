package com.inzynieria.insurance.controller;

import com.inzynieria.insurance.repository.OfferRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contract")
public class ContractController {


    private static final Logger LOGGER = LoggerFactory.getLogger(ContractController.class);

    @Autowired
    OfferRepository offerRepository;


}
