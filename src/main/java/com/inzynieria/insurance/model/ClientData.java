package com.inzynieria.insurance.model;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Klasa modelowa, która odwzorowuje encję dane klienta (clientdata) w bazie danych. Jest ona niezbędna jeśli w projekcie chcemy korzystać z frameworka Hibrenate.
 */
@Data
@Entity
public class ClientData {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idClientData;
    private Integer userId;
    private String idNumber;
    private String pesel;
    private String phone;
    private String place;
    private Integer houseNr;
    private Integer flatNr;
    private String street;
    private String postalCode;

    public ClientData() {
    }

    public Integer getIdClientData() {
        return idClientData;
    }

    public void setIdClientData(Integer idClientData) {
        this.idClientData = idClientData;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Integer getHouseNr() {
        return houseNr;
    }

    public void setHouseNr(Integer houseNr) {
        this.houseNr = houseNr;
    }

    public Integer getFlatNr() {
        return flatNr;
    }

    public void setFlatNr(Integer flatNr) {
        this.flatNr = flatNr;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
