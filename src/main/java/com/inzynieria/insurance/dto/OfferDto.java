package com.inzynieria.insurance.dto;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class OfferDto {

    public OfferDto(){

        }
        public OfferDto(Integer idoffer, String name, String information, Integer idclient, String insurancetype, String duration, String paymentAmount){

            this.idoffer=idoffer;
            this.name=name;
            this.information=information;
            this.idclient=idclient;
            this.insurancetype=insurancetype;
            this.duration=duration;
            this.paymentAmount=paymentAmount;


        }

    public Integer getIdoffer() {
        return idoffer;
    }

    public void setIdoffer(Integer idoffer) {
        this.idoffer = idoffer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Integer getIdclient() {
        return idclient;
    }

    public void setIdclient(Integer idclient) {
        this.idclient = idclient;
    }

    public String getInsurancetype() {
        return insurancetype;
    }

    public void setInsurancetype(String insurancetype) {
        this.insurancetype = insurancetype;
    }


    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    private Integer idoffer;
    private String name;
    private String information;
    private Integer idclient;
    private String insurancetype;
    private String duration;
    private String paymentAmount;

}
