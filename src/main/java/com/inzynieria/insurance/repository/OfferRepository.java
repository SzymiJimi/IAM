package com.inzynieria.insurance.repository;


import com.inzynieria.insurance.dto.OfferDto;
import com.inzynieria.insurance.model.Offer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OfferRepository extends JpaRepository<Offer,Integer> {

    List<Offer> findOfferByName(String name);

    //List<OfferDto> findOffer(String value);
//    @Query(value= "SELECT  o.idoffer, o.information, o.insurancetype, o.name, o.duration, o.paymenAmount from Offer o where o.idUser=userRoles.USER_IDUSER AND ( o.username= :data OR o.name= :data OR o.surname= :data OR o.email= :data)  AND userRoles.name = 'ROLE_CLIENT'", nativeQuery = true)
//    List<Offer> findOffer(@Param("data") String data);
}
