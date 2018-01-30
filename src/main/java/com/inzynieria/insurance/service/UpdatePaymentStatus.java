package com.inzynieria.insurance.service;


import com.inzynieria.insurance.model.Payments;
import com.inzynieria.insurance.repository.PaymentRepository;
import javafx.beans.InvalidationListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@EnableScheduling
@Service
public class UpdatePaymentStatus extends Observable {

    String value;

    @Autowired
    PaymentRepository paymentRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdatePaymentStatus.class);

    @Scheduled(cron = "0 0 12 1/1 * ? ")
    public void updatePaymentsStatuses() {
        LOGGER.info("Aktualizacja stanów płatności klienta");
        List<Payments> paymentsList = getPayments();
        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date today = calendar.getTime();
//        format1.format(startDate.getTime());
        Date expitationDate = new Date();
        if (paymentsList != null) {
            for (Payments payment : paymentsList) {
                try {
                    expitationDate = format.parse(payment.getExpitation_Date());
                    if(expitationDate.before(today))
                    {
                        payment.updatePayment();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        }
    }

//    public void updatePayment(){
//        LOGGER.info("Weszło do update w płatnościach");
//
//        setChanged();
//        notifyObservers("Siema");
//
//    }
    private List<Payments> getPayments() {

        List<Payments> paymentsList = new ArrayList<>();
        try {
            paymentsList = paymentRepository.findAll();
        } catch (Exception e) {
            LOGGER.info("Pobieranie płatności w bazy danych się nie powiodło, wyjątek: " + e.toString());
        }

        return paymentsList;
    }


//    @Override
//    public void addListener(InvalidationListener listener) {
//
//    }
//
//    @Override
//    public void removeListener(InvalidationListener listener) {
//
//    }
}
