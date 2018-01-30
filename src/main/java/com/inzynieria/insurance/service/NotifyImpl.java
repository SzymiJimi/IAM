package com.inzynieria.insurance.service;

import com.inzynieria.insurance.model.Notification;
import com.inzynieria.insurance.repository.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Service
public class NotifyImpl implements Notify {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotifyImpl.class);

    @Autowired
    NotificationRepository notificationRepository;

    public void notificationCreate(String type, String description, Integer idContract){

        Notification notification= new Notification();
        notification.setType(type);
        notification.setValuation("1");
        notification.setIncident_description(description);
        notification.setIdContract(idContract);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        notification.setIncident_time(format.format(calendar.getTime()));
        notification.setStatus("CREATED");
        notification.setIdNotification(100);
        // notification.setValuation(null);
        // LOGGER.info("Przed zapisaniem, typ: "+notification.getType()+", status: "+notification.getStatus() + ", czas: "+notification.getIncident_time() + ", opis: "+notification.getIncident_description() +", wycena: "+ notification.getValuation() + ", idumowy: "+notification.getIdContract());
        // notification= notificationConfigurer.configure(notification);
        LOGGER.info("Przed zapisaniem, typ: "+notification.getType()+", status: "+notification.getStatus() + ", czas: "+notification.getIncident_time() + ", opis: "+notification.getIncident_description() +", wycena: "+ notification.getValuation() + ", idumowy: "+notification.getIdContract());
        //  try{
        notificationRepository.save(notification);

    }

}
