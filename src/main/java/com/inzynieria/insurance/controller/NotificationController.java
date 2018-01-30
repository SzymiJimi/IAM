package com.inzynieria.insurance.controller;



import com.inzynieria.insurance.model.Notification;
import com.inzynieria.insurance.repository.NotificationRepository;
import com.inzynieria.insurance.service.NotificationConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * NotificationController zajmuje się przechwytywaniem żądań powiązanych ze zgłoszeniami. Umożliwia odbiór żądań przysyłanych z AngularaJS.
 */
@RestController
@RequestMapping("/notification")
public class NotificationController {

    /**
     * Finalny statyczny obiekt loggera służący do wyświetlania informacji o czasie oraz miejscu wystpienia błędu w konsoli lub w pliku.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    NotificationConfigurer notificationConfigurer;

    /**
     *  Zajmuje się odbiorem i obsługą żądania dotyczącego tworzenia nowego zgłoszenia do systemu.
     * @param notification Ciało żądania zawiera obiekt zgłoszenia, którego będziemy dodawać do naszego systemu.
     * @return.
     */
    @RequestMapping(value="/add", method = RequestMethod.POST)
    public ResponseEntity createContract(@RequestBody Notification notification){
        notification= notificationConfigurer.configure(notification);
        try{
            notificationRepository.save(notification);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }catch(Exception e)
        {
            LOGGER.info("Nieudane dodanie zgłoszenia do bazy...");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nie udało się dodać...");
        }
    }

    /**
     * Zajmuje się odbiorem i obsługą żądania dotyczącego przeglądania zgłoszeń.
     * @return
     */
    @RequestMapping(value="/getList", method = RequestMethod.GET)
    public ResponseEntity getContractsWithOffers(){

        try{
            List<Notification> notifications= notificationRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(notifications);
        }catch(Exception e)
        {
            LOGGER.info("Nieudany odczyt zgłoszeń z bazy...");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nie udało się odczytać danych...");
        }
    }
    /**
     * Zajmuje się odbiorem i obsługą żądania dotyczącego przeglądania zgłoszenia o podanym identyfikatorze.
     * @param value Identyfikator zgłoszenia
     * @return Szukane zgłoszenie
     */
    @RequestMapping(value = "/show", method = RequestMethod.POST)
    public Notification showNotification(@RequestBody Integer value)
    {
        Notification notification = notificationRepository.findOne(value);
        return notification;
    }

    /**
     * Metoda zajmująca się odbiorem żądania typu GET dotyczącego znalezienia zgłoszenia o podanym ID.
     * @param id Identyfikator zgłoszenia
     * @return Zwraca zgłoszenia o podanym ID
     */
    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public ModelAndView runTemplate(@PathVariable(value="id") Integer id)
    {
        ModelAndView mav = new ModelAndView("/notification/showData");
        return mav;
    }

    /**
     *  Zajmuje się odbiorem i obsługą żądania dotyczącego przeglądania zgłoszenia klienta o podanym numerze PESEL.
     * @param pesel Numer PESEL użytkownika
     * @return Zgłoszenia użytkownika, którego numer PESEL był podany
     */
    @RequestMapping(value = "/getUserNotification", method = RequestMethod.POST)
    public ResponseEntity getUserNotification(@RequestBody String pesel){
        try{
            List<Notification> notifications= notificationRepository.findNotificationsByUserPesel(pesel);
            return ResponseEntity.status(HttpStatus.OK).body(notifications);
        }catch(Exception e)
        {
            LOGGER.info("Nieudany odczyt zgłoszeń z bazy...");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nie udało się odczytać danych...");
        }
    }

    /**
     *
     * @param value
     * @return
     */
    @RequestMapping(value = "/redirect", method = RequestMethod.POST)
    public ResponseEntity redirectNotification(@RequestBody Integer value)
    {
        try {
            Notification notification = notificationRepository.findOne(value);
            notification.setStatus(NotificationConfigurer.Status.IVESTIGATING.toString());
            notificationRepository.save(notification);
            return ResponseEntity.status(HttpStatus.OK).body("Operacja przebiegła pomyślnie!");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Nieudane operacje na bazie danych");
            }

    }
}
