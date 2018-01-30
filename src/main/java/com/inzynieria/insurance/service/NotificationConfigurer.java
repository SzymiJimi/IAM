package com.inzynieria.insurance.service;

import com.inzynieria.insurance.controller.NotificationController;
import com.inzynieria.insurance.model.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Service
public class NotificationConfigurer {
    /**
     * Finalny statyczny obiekt loggera służący do wyświetlania informacji o czasie oraz miejscu wystpienia błędu w konsoli lub w pliku.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationConfigurer.class);


    public enum Status{
        CREATED, AVAITING_VALUATION, REJECTED, IVESTIGATING, CONFIRMED, ENDED
    }

    public Notification configure(Notification notification){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        notification.setIncident_time(format.format(calendar.getTime()));
        notification.setStatus(Status.CREATED.toString());
        return notification;
    }

}
