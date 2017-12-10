package com.inzynieria.insurance.service;

import com.inzynieria.insurance.model.Offer;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface OfferService {

   List<Offer> getUserByName(String name) throws ValidationException;
}
