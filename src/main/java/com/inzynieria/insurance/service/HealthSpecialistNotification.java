package com.inzynieria.insurance.service;

public class HealthSpecialistNotification extends AbstractSpecialNotification {

    public HealthSpecialistNotification(String type){
        this.type= type;
    }

    @Override
    protected void sendRedirect(){

    }

}
