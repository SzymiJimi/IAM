package com.inzynieria.insurance.service;

import com.inzynieria.insurance.model.Offer;

import com.inzynieria.insurance.repository.OfferRepository;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;

@Service
public class OfferServiceImpl implements OfferService{
    /**
     * Repozytorium ofert
     */
    OfferRepository offerRepository;
    
    @Override
    public List<Offer> getUserByName(String name) throws ValidationException {
        return offerRepository.findOfferByName(name);
    }
}
