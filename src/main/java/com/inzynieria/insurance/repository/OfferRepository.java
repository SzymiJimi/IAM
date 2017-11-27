package com.inzynieria.insurance.repository;


import com.inzynieria.insurance.model.Offer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface OfferRepository extends CrudRepository<Offer,Integer> {

}
