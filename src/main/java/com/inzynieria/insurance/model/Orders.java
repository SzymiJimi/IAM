package com.inzynieria.insurance.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idOrders;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String postalCode;
    private String fillingDate;
    private String status;

    public Orders() {
    }

    public Orders(String name, String surname, String email, String phone, String postalCode, String fillingDate, String status) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.postalCode = postalCode;
        this.fillingDate = fillingDate;
        this.status = status;
    }

    public Integer getIdOrder() {
        return idOrders;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrders = idOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getFillingDate() {
        return fillingDate;
    }

    public void setFillingDate(String fillingDate) {
        this.fillingDate = fillingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
