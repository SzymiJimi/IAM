package com.inzynieria.insurance.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Observable;

@EqualsAndHashCode(callSuper=false)
@Data
@Entity
public class Payments extends Observable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idPayments;
    private Integer amount;
    private String expitation_Date;
    private String payment_Date;
    private Integer user_idUser;
    private Integer idContract;
    private Integer regulated;

    public Payments(){

    }

    public Payments(Integer amount, String expitation_Date, String payment_Date, Integer user_idUser, Integer idContract, Integer regulated) {
        this.amount = amount;
        this.expitation_Date = expitation_Date;
        this.payment_Date = payment_Date;
        this.user_idUser = user_idUser;
        this.idContract = idContract;
        this.regulated = regulated;
    }

    public Integer getIdPayments() {
        return idPayments;
    }

    public void setIdPayments(Integer idPayments) {
        this.idPayments = idPayments;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getExpitation_Date() {
        return expitation_Date;
    }

    public void setExpitation_Date(String expitation_Date) {
        this.expitation_Date = expitation_Date;
    }

    public String getPayment_Date() {
        return payment_Date;
    }

    public void setPayment_Date(String payment_Date) {
        this.payment_Date = payment_Date;
    }

    public Integer getUser_idUser() {
        return user_idUser;
    }

    public void setUser_idUser(Integer user_idUser) {
        this.user_idUser = user_idUser;
    }

    public Integer getIdContract() {
        return idContract;
    }

    public void setIdContract(Integer idContract) {
        this.idContract = idContract;
    }

    public Integer getRegulated() {
        return regulated;
    }

    public void setRegulated(Integer regulated) {
        this.regulated = regulated;
    }

    public void updatePayment(Object payment){

        setChanged();
        notifyObservers(payment);
    }
}
