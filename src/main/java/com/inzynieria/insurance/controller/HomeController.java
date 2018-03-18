package com.inzynieria.insurance.controller;


import com.inzynieria.insurance.commands.config.CommandsConfig;
import com.inzynieria.insurance.service.UpdateContractStatus;
import com.inzynieria.insurance.service.UpdatePaymentStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * Główny kontroller zajmujący się przekierowaniami, oraz uruchamianiem konkretnych template'ów. Kontroluje on także dostęp do poszczególnych adresów.
 */
@Controller
public class HomeController {

    @Autowired
    UpdateContractStatus updateContractStatus;

    @Autowired
    UpdatePaymentStatus updatePaymentStatus;


    /**
     * Statyczny obiekt loggera służący do wyświetlania informacji o czasie oraz miejscu wystpienia błędu w konsoli lub w pliku.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    /**
     * Metoda zajmująca się odbiorem żądania uruchomienia widoku home.
     * @return zwraca nazwę template'a, który zostanie uruchomiony
     */
    @RequestMapping(value = "/home")
    public String home() {
        CommandsConfig.createCommands();
        return "home";
    }

    /**
     * Metoda zajmująca się odbiorem żądania uruchomienia widoku startowego.
     * @return zwraca nazwę template'a, który zostanie uruchomiony, w tym przypadku home.
     */
    @RequestMapping(value = "/")
    public ModelAndView start(final Principal principal, ModelMap model) {
        if (null == principal) {
            return new ModelAndView("index");
        }
        return new ModelAndView("redirect:/home", model);
    }

    /**
     * Metoda zajmująca się odbiorem żądania uruchomienia widoku accesDenied.
     * @return zwraca nazwę template'a, który zostanie uruchomiony.
     */
    @RequestMapping(value = "/accessDenied")
    public String accesDenied() {
        CommandsConfig.createCommands();
        return "accessDenied";
    }

    /**
     * Metoda zajmująca się odbiorem żądania uruchomienia widoku login.
     * @return zwraca nazwę template'a, który zostanie uruchomiony.
     */
    @RequestMapping(value = "/login")
    public ModelAndView login(final Principal principal, ModelMap model) {
        if (null != principal) {
            return new ModelAndView("redirect:/home", model);
        }
        return new ModelAndView("login/login");
    }

    @RequestMapping(value = "/login/orderInsurance")
    public String orderInsurance() {
        return "login/orderInsurance";
    }

    /**
     * Metoda zajmująca się odbiorem żądania uruchomienia widoku wyszukiwania usera.
     * @return zwraca nazwę template'a, który zostanie uruchomiony.
     */
    @RequestMapping(value = "/find/findUser")
    public String findUser() {
        updateContractStatus.updateContractStatuses();
        updatePaymentStatus.updatePaymentsStatuses();
        return "find/findUser";
    }

    /**
     * Metoda zajmująca się odbiorem żądania uruchomienia widoku wyszukiwania klienta. Dostęp jest zabezpieczony i nie wszyscy użytkownicy mają dostęp do tego widoku.
     * @return zwraca nazwę template'a, który zostanie uruchomiony.
     */
    @PreAuthorize("hasRole('AGENT')")
    @RequestMapping(value = "/find/findClient")
    public ModelAndView findClient() {
        ModelAndView mav = new ModelAndView("find/findClient");
        return mav;
    }

    /**
     * Metoda zajmująca się odbiorem żądania uruchomienia widoku tworzenia nowego kontraktu. Dostęp jest zabezpieczony i nie wszyscy użytkownicy mają dostęp do tego widoku.
     * @return zwraca nazwę template'a, który zostanie uruchomiony.
     */
    @PreAuthorize("hasRole('AGENT')")
    @RequestMapping(value = "/new/contract")
    public ModelAndView newContract() {

        ModelAndView mav = new ModelAndView("contract/new");
        return mav;
    }

    /**
     * Metoda zajmująca się odbiorem żądania uruchomienia widoku tworzenia nowego zgłoszenia. Dostęp jest zabezpieczony i nie wszyscy użytkownicy mają dostęp do tego widoku.
     * @return zwraca nazwę template'a, który zostanie uruchomiony.
     */
    @PreAuthorize("hasRole('CONSULTANT')")
    @RequestMapping(value = "/new/notification")
    public ModelAndView newNotification() {
        ModelAndView mav = new ModelAndView("notification/new");
        return mav;
    }

    /**
     * Metoda zajmująca się odbiorem żądania uruchomienia widoku wyświetlania listy wszystkich zgłoszeń. Dostęp jest zabezpieczony i nie wszyscy użytkownicy mają dostęp do tego widoku.
     * @return zwraca nazwę template'a, który zostanie uruchomiony
     */
    @PreAuthorize("hasRole('AGENT')")
    @RequestMapping(value = "/show/notifications")
    public ModelAndView showNotification() {
        ModelAndView mav = new ModelAndView("notification/showList");
        return mav;
    }

    /**
     * Metoda zajmująca się odbiorem żądania uruchomienia widoku tworzenia nowej oferty. Dostęp jest zabezpieczony i nie wszyscy użytkownicy mają dostęp do tego widoku.
     * @return zwraca nazwę template'a, który zostanie uruchomiony.
     */
    @PreAuthorize("hasRole('AGENT')")
    @RequestMapping(value = "/new/offer")
    public ModelAndView addOffer() {
        ModelAndView mav = new ModelAndView("offer/offerAdd");
        return mav;
    }

    /**
     * Metoda zajmująca się odbiorem żądania uruchomienia widoku wyświetlania danych klienta.
     * @return zwraca nazwę template'a, który zostanie uruchomiony.
     */
    @RequestMapping(value = "/client/clientData")
    public String clientData() {
        return "client/clientData";
    }

    /**
     * Metoda zajmująca się odbiorem żądania uruchomienia widoku sprawdzania ubezpieczenia klienta. Dostęp jest zabezpieczony i nie wszyscy użytkownicy mają dostęp do tego widoku.
     * @return zwraca nazwę template'a, który zostanie uruchomiony.
     */
    @PreAuthorize("hasRole('SPECIALIST')")
    @RequestMapping(value = "/client/checkInsurance")
    public String clientInsurance() {
        return "client/checkInsurance";
    }

    /**
     * Metoda zajmująca się odbiorem żądania uruchomienia widoku wyszukiwania ofert. Dostęp jest zabezpieczony i nie wszyscy użytkownicy mają dostęp do tego widoku.
     * @return zwraca nazwę template'a, który zostanie uruchomiony.
     */
    @PreAuthorize("hasRole('AGENT')  or hasRole('CLIENT')")
    @RequestMapping(value = "/find/findOffer")
    public ModelAndView findOffer() {
        LOGGER.info("Jestem w find offer w home");
        ModelAndView mav = new ModelAndView("find/findOffer");
        return mav;
    }

    /**
     * Metoda zajmująca się odbiorem żądania uruchomienia widoku wyszukiwania wniosków. Dostęp jest zabezpieczony i nie wszyscy użytkownicy mają dostęp do tego widoku.
     * @return zwraca nazwę template'a, który zostanie uruchomiony.
     */
    @PreAuthorize("hasRole('AGENT')")
    @RequestMapping(value = "/find/findApplication")
    public String findApplication() {
        return "find/findApplication";
    }

    /**
     * Metoda zajmująca się odbiorem żądania uruchomienia widoku tworzenia nowego wniosku. Dostęp jest zabezpieczony i nie wszyscy użytkownicy mają dostęp do tego widoku.
     * @return zwraca nazwę template'a, który zostanie uruchomiony.
     */
    @PreAuthorize("hasRole('CLIENT')")
    @RequestMapping(value = "/application/applicationAdd")
    public String addApplication() {
        LOGGER.info("Jestem w find offer w home");
        return "application/applicationAdd";
    }

    /**
     * Metoda zajmująca się odbiorem żądania uruchomienia widoku wyświetlającego dany wniosek.
     * @return zwraca nazwę template'a, który zostanie uruchomiony.
     */
    @RequestMapping(value = "/application/applicationData")
    public String applicationData() {
        return "application/applicationData";
    }

    @RequestMapping(value= "/client/homeReserve")
    public String addUser()
    {
        return "client/homeReserve";
    }

    @RequestMapping(value= "/show/offers")
    public String showOffers()
    {
        return "offer/showAll";
    }

    @RequestMapping(value="/changePass")
    public String changePassword(){
        return "user/changePass";
    }


}
