package com.inzynieria.insurance.dto;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class OfferDto {

        public OfferDto(){

        }
        public OfferDto(Integer idoffer, String name, String information, Integer idclient, String insurancetype, Integer user_start_age, Integer user_end_age,  String offercol){
            this.idoffer=idoffer;
            this.name=name;
            this.information=information;
            this.idclient=idclient;
            this.insurancetype=insurancetype;
            this.user_start_age=user_start_age;
            this.user_end_age=user_end_age;
            this.offercol=offercol;

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

    public Integer getUser_start_age() {
        return user_start_age;
    }

    public void setUser_start_age(Integer user_start_age) {
        this.user_start_age = user_start_age;
    }

    public Integer getUser_end_age() {
        return user_end_age;
    }

    public void setUser_end_age(Integer user_end_age) {
        this.user_end_age = user_end_age;
    }

    public String getOffercol() {
        return offercol;
    }

    public void setOffercol(String offercol) {
        this.offercol = offercol;
    }

    private Integer idoffer;
    private String name;
    private String information;
    private Integer idclient;
    private String insurancetype;
    private Integer user_start_age;
    private Integer user_end_age;
    private String offercol;
}
