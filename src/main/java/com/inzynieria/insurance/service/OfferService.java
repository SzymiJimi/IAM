package com.inzynieria.insurance.service;

import com.inzynieria.insurance.model.Offer;

import javax.xml.bind.ValidationException;
import java.util.List;

/**
 * Interfejs do serwisów ofert
 */
public interface OfferService {
   /**
    * Znajdowanie ofert po nazwie
    * @param name nazwa oferty
    * @return Lista ofert
    * @throws ValidationException
    */
   List<Offer> getUserByName(String name) throws ValidationException;
}
