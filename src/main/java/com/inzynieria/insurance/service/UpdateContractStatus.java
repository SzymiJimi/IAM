package com.inzynieria.insurance.service;


import com.inzynieria.insurance.model.Contract;
import com.inzynieria.insurance.model.Payments;
import com.inzynieria.insurance.repository.ContractRepository;
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
public class UpdateContractStatus {

    @Autowired
    ContractRepository contractRepository;

    @Autowired
    NotificationCreator notificationCreator;

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdatePaymentStatus.class);

    @Scheduled(cron = "0 0 12 1/1 * ? ")
    public void updateContractStatuses() {
        LOGGER.info("Aktualizacja stanów umów z klientami");
        List<Contract> contractList = getContracts();
        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date today = calendar.getTime();
//        format1.format(startDate.getTime());
        Date expitationDate = new Date();
        if (contractList != null) {
            for (Contract contract : contractList) {
                try {
                    expitationDate = format.parse(contract.getExpirationDate());
                    if(expitationDate.before(today))
                    {
                        contract.addObserver(notificationCreator);
                        contract.updateContract(contract);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private List<Contract> getContracts() {

        List<Contract> contractList = new ArrayList<>();
        try {
            contractList = contractRepository.findAll();
        } catch (Exception e) {
            LOGGER.info("Pobieranie umów z bazy danych się nie powiodło, wyjątek: " + e.toString());
        }
        return contractList;
    }

}
