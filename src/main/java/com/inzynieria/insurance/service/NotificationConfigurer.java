package com.inzynieria.insurance.service;

import com.inzynieria.insurance.model.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Serwis do konfigurowania zgłoszeń
 */
@Service
public class NotificationConfigurer {
    /**
     * Finalny statyczny obiekt loggera służący do wyświetlania informacji o czasie oraz miejscu wystpienia błędu w konsoli lub w pliku.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationConfigurer.class);

    /**
     * Typ wyliczeniowy dla zgłoszenia
     */
    public enum Status{
        CREATED, AVAITING_VALUATION, REJECTED, IVESTIGATING, CONFIRMED, ENDED
    }

    /**
     * Konfiguracja poczatkowa zgłoszenia
     * @param notification obiekt zgłoszenia
     * @return obiekt zgłoszenia po modyfikacji
     */
    public Notification configure(Notification notification){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        notification.setIncident_time(format.format(calendar.getTime()));
        notification.setStatus(Status.CREATED.toString());
        return notification;
    }

}
