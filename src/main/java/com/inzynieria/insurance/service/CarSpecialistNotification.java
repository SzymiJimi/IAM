package com.inzynieria.insurance.service;

public class CarSpecialistNotification extends AbstractSpecialNotification {

    public CarSpecialistNotification(String type){
        this.type= type;
    }

    @Override
    protected void sendRedirect(){

    }

}
