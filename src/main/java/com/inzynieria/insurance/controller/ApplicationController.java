package com.inzynieria.insurance.controller;


import com.inzynieria.insurance.model.Application;
import com.inzynieria.insurance.model.User;
import com.inzynieria.insurance.repository.ApplicationRepository;
import com.inzynieria.insurance.repository.UserRepository;
import com.inzynieria.insurance.service.SendMailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.List;
/**
 * ApplicationController zajmuje się przechwytywaniem żądań powiązanych z wnioskami. Umożliwia odbiór żądań przysyłanych z AngularaJS.
 */
@RestController
@RequestMapping("/application")
public class ApplicationController {
    /**
     * Finalny statyczny obiekt loggera służący do wyświetlania informacji o czasie oraz miejscu wystpienia błędu w konsoli lub w pliku.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationController.class);

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    UserRepository userRepository;
    /**
     *  Zajmuje się odbiorem i obsługą żądania dotyczącego tworzenia nowych wniosków.
     * @param application Ciało żądania zawiera obiekt wniosku, którego będziemy dodawać do naszego systemu.
     * @return Zwraca informacje, o dodaniu w przypadku powodzenia, w przypadku błędu, zwraca informacje o niepowodzeniu.
     */
    @RequestMapping(value="/add", method = RequestMethod.POST)
    public String createApplication(@RequestBody Application application){

        LOGGER.info("Dodawanie wniosku");
        if(applicationRepository.save(application)!=null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipalName = authentication.getName();
            User user = userRepository.findByUsername(currentPrincipalName);
            SendMailService sendMailService = new SendMailService(user.getEmail(), "Wysłano wniosek dnia: "+ application.getFilling_date() + "\nDziękujemy!", "Zgłoszenie wyslania wniosku");
            sendMailService.send();
            return "Dodano wniosek pomyślnie";
        }
        else{
            return "Wystąpił błąd przy dodawaniu wniosku!";
        }

    }

    /**
     * Metoda zajmująca się odbiorem żądania dotyczącego znalezienia listy wniosków w zależności od roli zalogowanego użytkownika
     * @return Zwraca listę znalezionych wmniosków.
     * @throws ValidationException
     */
    @RequestMapping(value="/find")
    public List <Application> findApplication () throws ValidationException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        String value="";
        List <String> roles= applicationRepository.roleForFind(currentPrincipalName);
        List <Application> applications= new ArrayList<>();
        List <Application> tmp;
        LOGGER.info(roles.get(1));
        for(String role: roles) {
            if ((role.compareTo("ROLE_CARSPECIALIST")) == 0) {
                value = "CAR";
                tmp = applicationRepository.findApplicationByType(value);
                applications.addAll(tmp);

            } else if ((role.compareTo("ROLE_HEALTHSPECIALIST")) == 0) {
                value = "HEALTH";
                tmp = applicationRepository.findApplicationByType(value);
                applications.addAll(tmp);
            }
            else if ((role.compareTo("ROLE_TRAVELSPECIALIST")) == 0) {
                value = "TRAVEL";
                tmp = applicationRepository.findApplicationByType(value);
                applications.addAll(tmp);
            }
        }
        return applications;
    }
    /**
     * Metoda zajmująca się odbiorem żądania dotyczącego znalezienia wniosku o podanym id.
     * @param id Id wniosku, którego chcemy znaleźć w bazie.
     * @return Zwraca znaleziony obiekt wniosku.
     * @throws ValidationException
     */
    @RequestMapping(value="/show/{id}", method = RequestMethod.GET)
    public Application findApplication(@PathVariable(value="id") Integer id)
    {
        LOGGER.info("Id pobrane: "+id);
        Application application= applicationRepository.findOne(id);
        return application;
    }
}
