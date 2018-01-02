package com.inzynieria.insurance.controller;

import com.inzynieria.insurance.model.Offer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.inzynieria.insurance.repository.OfferRepository;
import com.inzynieria.insurance.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping(value="/offer")
public class OfferController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OfferController.class);

    @Autowired
    OfferRepository offerRepository;
    @Autowired
    OfferService offerService;

    @RequestMapping(value="/add", method = RequestMethod.POST)

    public String createOffer(@RequestBody Offer offer){

        LOGGER.info("Dodawanie informacji oferty do bazy");
        offerRepository.save(offer);
        return "Dodano pomyślnie";
    }



    @RequestMapping(value="/find")
    public List<Offer> findOffer(@RequestBody String value)
    {
        LOGGER.info("Wyszukiwanie oferty w bazie");
        List<Offer> offers= offerRepository.findOfferByName(value);
        LOGGER.info("Ilosc znalezionych offert: "+ offers.size());
        return offers;
    }

    @RequestMapping(value="/showData")
    public String show()
    {
        LOGGER.info("Jestem tutaj");
        return "";
    }


    @RequestMapping(value = "/show")
    public Offer setOfferData(@RequestBody Integer value) {
        Offer offer =offerRepository.findOne(value);
        return offer;
    }


    @RequestMapping(value = "/show/{id}")
    public ModelAndView showOffer(@PathVariable(value="id") Integer id)
    {
        ModelAndView mav = new ModelAndView("/offer/offerData");
       Offer offer  = offerRepository.findOne(id);
        mav.addObject("name",offer.getOffercol());
        mav.addObject("name",offer.getOffercol());
        return mav;
    }

}
