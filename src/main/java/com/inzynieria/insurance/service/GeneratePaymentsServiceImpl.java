package com.inzynieria.insurance.service;


import com.inzynieria.insurance.model.Offer;
import com.inzynieria.insurance.model.Payments;
import com.inzynieria.insurance.repository.OfferRepository;
import com.inzynieria.insurance.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class GeneratePaymentsServiceImpl implements GeneratePaymentsService {

    @Autowired
    OfferRepository offerRepository;

    @Autowired
    PaymentRepository paymentRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(GeneratePaymentsServiceImpl.class);

    @Override
    public void generatePayments(Integer idContract, Integer userId, Integer idOffer, String startDateString) {
        Offer offer = new Offer();
        Integer amount, duration, month = 0, year = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date tmpDate = new Date();
        try {
            tmpDate = format.parse(startDateString);
        } catch (ParseException e) {
            LOGGER.info("Formatowanie daty się nie powiodło");
        }

        Calendar startDate = Calendar.getInstance();
        startDate.setTime(tmpDate);
        LOGGER.info("Przyjete dane: " + idOffer + " userId: " + userId + " startDate: " + format.format(startDate.getTime()));
        try {
            LOGGER.info("Id oferty:" + idOffer);
            offer = offerRepository.findOne(idOffer);
        } catch (Exception e) {
            LOGGER.info("Wystąpił błąd przy pobieraniu oferty z bazy..." + offer.getName());
        }
        if (offer != null) {
            amount = Integer.parseInt(offer.getPaymentAmount());
            duration = Integer.parseInt(offer.getDuration());
            Calendar tmpCalendar = Calendar.getInstance();

            for (int i = 0; i < duration; i++) {
                if (month == 12) {
                    month = 0;
                    year++;
                }
                tmpCalendar.set(startDate.get(Calendar.YEAR) + year, startDate.get(Calendar.MONTH) + month, startDate.get(Calendar.DATE));
                Payments payment = new Payments(amount, format.format(tmpCalendar.getTime()), null, userId, idContract, 0);
                NotificationCreator notificationCreator= new NotificationCreator();
                payment.addObserver(notificationCreator);
                paymentRepository.save(payment);
                month++;
            }

        }
    }

}
