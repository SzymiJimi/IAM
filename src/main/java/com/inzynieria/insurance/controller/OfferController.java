package com.inzynieria.insurance.controller;

import com.inzynieria.insurance.model.Offer;
import com.inzynieria.insurance.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/offer")
public class OfferController {

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
        offer.setUser_start_age(24);
        offer.setUser_end_age(26);
        offerRepository.save(offer);
    }
}
