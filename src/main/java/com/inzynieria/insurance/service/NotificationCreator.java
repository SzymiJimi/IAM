package com.inzynieria.insurance.service;

import com.inzynieria.insurance.model.Contract;
import com.inzynieria.insurance.model.Payments;
import com.inzynieria.insurance.repository.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Observable;
import java.util.Observer;

@Service
public class NotificationCreator implements Observer {


    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    NotificationConfigurer notificationConfigurer;

    @Autowired
    Notify notify;

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationCreator.class);

    @Override
    public void update(Observable o, Object arg) {

        Notify notify = new NotifyImpl();
        if(o.getClass()== Contract.class){
            LOGGER.info("Weszło w notyfikacje umów");
            Contract contract= (Contract)arg;

            notify.notificationCreate("Koniec umowy", "Umowa klienta dobiegła końca!", contract.getIdContract());
        }else{
            if(o.getClass()== Payments.class){
                LOGGER.info("Weszło w notyfikacje płatności");
                Payments payment= (Payments)arg;
                notify.notificationCreate("Nieuregulowana płatność", "Klient nr: "+payment.getUser_idUser()+" nie uregulował płatności nr: "+payment.getIdPayments()+" ważnej do: "+payment.getExpitation_Date(), payment.getIdContract());
            }
        }

        LOGGER.info("Wywołał się updejt dla obiektu: "+o.toString()+" argument: "+arg.toString() );
    }

//    private void notificationCreate(String type, String description, Integer idContract){
//
//        Notification notification= new Notification();
//        notification.setType(type);
//       // notification.setStatus(NotificationConfigurer.Status.CREATED.toString());
//       // Calendar calendar= Calendar.getInstance();
//       // SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//       // notification.setIncident_time(format.format(calendar.getTime()));
//        notification.setValuation("1");
//        notification.setIncident_description(description);
//        notification.setIdContract(idContract);
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        Calendar calendar = Calendar.getInstance();
//        notification.setIncident_time(format.format(calendar.getTime()));
//        notification.setStatus("CREATED");
//       // notification.setValuation(null);
//       // LOGGER.info("Przed zapisaniem, typ: "+notification.getType()+", status: "+notification.getStatus() + ", czas: "+notification.getIncident_time() + ", opis: "+notification.getIncident_description() +", wycena: "+ notification.getValuation() + ", idumowy: "+notification.getIdContract());
//       // notification= notificationConfigurer.configure(notification);
//        LOGGER.info("Przed zapisaniem, typ: "+notification.getType()+", status: "+notification.getStatus() + ", czas: "+notification.getIncident_time() + ", opis: "+notification.getIncident_description() +", wycena: "+ notification.getValuation() + ", idumowy: "+notification.getIdContract());
//      //  try{
//            notificationRepository.save(notification);
//       // }catch (Exception e)
//       // {
//          //  LOGGER.info("Nieudane dodanie do bazy zgłoszenia, wyjątek: "+ e.toString());
//       // }
//    }
}
