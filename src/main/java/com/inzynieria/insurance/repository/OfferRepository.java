package com.inzynieria.insurance.repository;


import com.inzynieria.insurance.model.Offer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OfferRepository extends JpaRepository<Offer,Integer> {

    List<Offer> findOfferByName(String name);
}
