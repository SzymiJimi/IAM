package com.inzynieria.insurance.service;

import com.inzynieria.insurance.model.Contract;
import com.inzynieria.insurance.model.Notification;
import com.inzynieria.insurance.model.Payments;
import com.inzynieria.insurance.repository.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;

@Service
public class NotificationCreator implements Observer {


    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    NotificationConfigurer notificationConfigurer;


    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationCreator.class);

    @Override
    public void update(Observable o, Object arg) {

        if(o.getClass()== Contract.class){
            Contract contract= (Contract)arg;
            notificationCreate("Koniec umowy", "Umowa klienta dobiegła końca!", contract.getIdContract());
        }else{
            if(o.getClass()== Payments.class){
                Payments payment= (Payments)arg;
                notificationCreate("Nieuregulowana płatność", "Klient nr: "+payment.getUser_idUser()+" nie uregulował płatności nr: "+payment.getIdPayments()+" ważnej do: "+payment.getExpitation_Date(), payment.getIdContract());
            }
        }
    }

    private void notificationCreate(String type, String description, Integer idContract){

        Notification notification= new Notification();
        notification.setType(type);
        notification.setValuation("1");
        notification.setIncident_description(description);
        notification.setIdContract(idContract);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        notification.setIncident_time(format.format(calendar.getTime()));
        notification.setStatus("CREATED");
        try{
            notificationRepository.save(notification);
        }catch (Exception e)
        {
            LOGGER.info("Nieudane dodanie do bazy zgłoszenia, wyjątek: "+ e.toString());
        }
    }
}
