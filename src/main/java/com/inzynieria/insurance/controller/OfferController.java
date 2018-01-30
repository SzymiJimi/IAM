package com.inzynieria.insurance.controller;

import com.inzynieria.insurance.dto.UserDto;
import com.inzynieria.insurance.model.Offer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.inzynieria.insurance.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
@RequestMapping(value = "/offer")
public class OfferController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OfferController.class);


    @Autowired
    OfferRepository offerRepository;

    public OfferController(OfferRepository offerRepository) {
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
   public String createOffer(@RequestBody Offer offer)
   {
        LOGGER.info("Klient dodaje oferte");
       offerRepository.save(offer);
       return "Dodano pomyslnie"; }



    @RequestMapping(value="/find")

    public List<Offer> findOffer(@RequestBody String value) throws ValidationException {

        LOGGER.info("Klient wyszukuje oferty w bazie");
        List<Offer> offers = offerRepository.findOfferByName(value);
        LOGGER.info("Ilosc znalezionych offert: " + offers.size());
        return offers;
    }

    @RequestMapping(value="/findOne", method = RequestMethod.POST)
    public Offer findOffer(@RequestBody Integer id)
    {

        LOGGER.info("Klient wyswielta dane oferty");
        Offer offer= offerRepository.findOne(id);
        return offer;
    }

    @RequestMapping(value = "/showData")
    public String show() {
        LOGGER.info("Jestem tutaj");
        return "";
    }


    @RequestMapping(value = "/show")
    public Offer setOfferData(@RequestBody Integer value) {
        Offer offer = offerRepository.findOne(value);
        return offer;
    }

    @RequestMapping(value = "/show/{id}")
    public ModelAndView showOffer(@PathVariable(value="id") Integer id)
    {
                ModelAndView mav = new ModelAndView("/offer/offerData");
               Offer offer  = offerRepository.findOne(id);
                mav.addObject("name",offer.getName());
                return mav;
            }


}
