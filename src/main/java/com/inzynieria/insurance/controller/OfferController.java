package com.inzynieria.insurance.controller;

import com.inzynieria.insurance.model.Offer;
import com.inzynieria.insurance.repository.OfferRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/offer")
public class OfferController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OfferController.class);


    @Autowired
    OfferRepository offerRepository;
    @RequestMapping(value="/add")
    public void create()
    {
        System.out.println("HAAAAAAAAAAAAAALO: wyświetlam informacje");
        Offer offer=new Offer();
        offer.setName("Przyklad");
        offer.setInformation("tezprzyklad");
        offer.setInsurancetype("1");
        offerRepository.save(offer);
    }

    @RequestMapping(value="/findOne", method = RequestMethod.POST)
    public Offer findOffer(@RequestBody Integer id)
    {
        LOGGER.info("Id pobrane: "+id);
        Offer offer= offerRepository.findOne(id);
        return offer;
    }

}
