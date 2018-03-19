package com.inzynieria.insurance.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.inzynieria.insurance.model.Offer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Klasa umożliwiająca połączenie dwóch klas modelowych w jedną. Jest to uzyteczne w przypadku wyświetlania danych umowy dla danego klienta. Połączenie danych z dwóch
 * encji wykonywane jest na poziomie kodu Javy unikając w ten sposób, łączenia tych obiektów w AngularzeJS.
 */
@Data
public class ContractWithOffer {

    private Integer idContract;
    private String startDate;
    private String expirationDate;
    private Integer idUser;
    private Offer offer;

    public ContractWithOffer (){

    }

    public ContractWithOffer(Integer idContract, String startDate, String expirationDate, Integer idUser, Offer offer) {
        this.idContract = idContract;
        this.startDate = startDate;
        this.expirationDate = expirationDate;
        this.idUser = idUser;
        this.offer = offer;
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

    public Offer getOffer() {
        return this.offer;
    }

    public void setOffer(Offer offer) {
        offer = offer;
    }
}
