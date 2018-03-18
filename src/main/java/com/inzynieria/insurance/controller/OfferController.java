package com.inzynieria.insurance.controller;

import com.inzynieria.insurance.model.Offer;
import com.inzynieria.insurance.service.CustomComparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.inzynieria.insurance.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.bind.ValidationException;
import java.util.Collections;
import java.util.List;

/**
 * OfferController zajmuje się przechwytywaniem żądań powiązanych z ofertami. Umożliwia odbiór żądań przysyłanych z AngularaJS.
 */
@RestController
@RequestMapping(value = "/offer")
public class OfferController {
    /**
     * Finalny statyczny obiekt loggera służący do wyświetlania informacji o czasie oraz miejscu wystpienia błędu w konsoli lub w pliku.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(OfferController.class);


    @Autowired
    OfferRepository offerRepository;


    /**
     * Zajmuje się odbiorem i obsługą żądania dotyczącego tworzenia nowej oferty.
     *
     * @param offer Ciało żądania zawiera obiekt oferty, którą będziemy dodawać do naszego systemu.
     * @return Zwraca informacje, o pozytywnym dodaniu oferty w przypadku powodzenia, w przypadku błędu, zwraca informacje o niepowodzeniu.
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String createOffer(@RequestBody Offer offer) {
        LOGGER.info("Dodaje oferte");
        if (offerRepository.save(offer) != null) {
            return "Dodano pomyslnie";
        } else
            return "Wystąpił błąd podczas dodawania oferty!";
    }

    /**
     * Metoda zajmująca się odbiorem żądania dotyczącego znalezienia listy ofert, których nazwa jest
     * taka sama jak przekazany parametr value.
     *
     * @param value Nazwa oferty
     * @return Lista ofert
     * @throws ValidationException
     */
    @RequestMapping(value = "/find")
    public List<Offer> findOffer(@RequestBody String value) throws ValidationException {

        LOGGER.info("Klient wyszukuje oferty w bazie");
        List<Offer> offers = offerRepository.findOfferByName(value);
        LOGGER.info("Ilosc znalezionych offert: " + offers.size());
        return offers;
    }

    /**
     * Metoda zajmująca się odbiorem żądania dotyczącego znalezienia oferty, której identyfikator jest przekazany przez parametr
     *
     * @param id Identyfikator oferty
     * @return Oferta o podanym id
     */
    @RequestMapping(value = "/findOne", method = RequestMethod.POST)
    public Offer findOffer(@RequestBody Integer id) {

        LOGGER.info("Klient wyswielta dane oferty");
        Offer offer = offerRepository.findOne(id);
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

    /**
     * Metoda wyswietlajaca szczegółowe dane dla konkretnej oferty
     *
     * @return zwraca widok na ta oferte
     */
    @RequestMapping(value = "/show/{id}")
    public ModelAndView showOffer(@PathVariable(value = "id") Integer id) {
        ModelAndView mav = new ModelAndView("/offer/offerData");
        Offer offer = offerRepository.findOne(id);
        mav.addObject("name", offer.getName());
        return mav;
    }

    @RequestMapping(value = "/showList")
    public ResponseEntity showList() {
        try{
            List<Offer> offers= offerRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(offers);
        }catch(Exception e) {
            LOGGER.info("Nieudany odczyt zgłoszeń z bazy...");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nie udało się odczytać danych...");
        }
    }
}
