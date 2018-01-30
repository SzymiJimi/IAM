package com.inzynieria.insurance.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.inzynieria.insurance.service.GeneratePaymentsService;
import com.inzynieria.insurance.service.GeneratePaymentsServiceImpl;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.Date;
/**
 * Klasa modelowa, która odwzorowuje encję umowa (contract) w bazie danych. Jest ona niezbędna jeśli w projekcie chcemy korzystać z frameworka Hibrenate.
 */
import java.util.Observable;
@Data
@Entity
public class Contract extends Observable {

//    @Autowired
//    GeneratePaymentsService generatePaymentsService;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idContract;
    private String startDate;
    private String expirationDate;
    private Integer idUser;
    private Integer idOffer;
    private Integer active;

    public Contract (){

    }

    public Contract(String startDate, String expirationDate, Integer idUser, Integer idOffer, Integer active) {

        this.startDate = startDate;
        this.expirationDate = expirationDate;
        this.idUser = idUser;
        this.idOffer = idOffer;
        this.active = active;
    }

    @JsonCreator
    public Contract(@JsonProperty("idUser") Integer idUser, @JsonProperty("idOffer")Integer idOffer) {

        this.idUser = idUser;
        this.idOffer = idOffer;
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        Calendar startDate = Calendar.getInstance();
        Calendar expirationDate = Calendar.getInstance();
        this.startDate=format1.format(startDate.getTime());
        expirationDate.set(startDate.get(Calendar.YEAR )+2, startDate.get(Calendar.MONTH), startDate.get(Calendar.DATE));
        this.expirationDate= format1.format(expirationDate.getTime());
        this.active=1;
    }


    public Contract(Integer idContract, String startDate, String expirationDate, Integer idUser, Integer idOffer, Integer active ) {
        this.idContract = idContract;
        this.startDate = startDate;
        this.expirationDate = expirationDate;
        this.idUser = idUser;
        this.idOffer = idOffer;
        this.active =  active;
    }

    public Integer getIdContract() {
        return this.idContract;
    }

    public void setIdContract(Integer idContract) {
        this.idContract = idContract;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String startDate) {
        startDate = startDate;
    }

    public String getExpirationDate() {
        return this.expirationDate;
    }

    public void setExpirationDate(String expiration_Date) {
        this.expirationDate = expiration_Date;
    }

    public Integer getIdUser() {
        return this.idUser;
    }

    public void setIdUser(Integer idUser) {
        idUser = idUser;
    }

    public Integer getIdOffer() {
        return this.idOffer;
    }

    public void setIdOffer(Integer idOffer) {
        idOffer = idOffer;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public void updateContract(Object contract){
        setChanged();
        notifyObservers(contract);
    }

}
