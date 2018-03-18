package com.inzynieria.insurance.controller;

import com.inzynieria.insurance.dto.ContractWithOffer;
import com.inzynieria.insurance.model.Contract;
import com.inzynieria.insurance.model.User;
import com.inzynieria.insurance.repository.ContractRepository;
import com.inzynieria.insurance.repository.OfferRepository;
import com.inzynieria.insurance.repository.UserRepository;
import com.inzynieria.insurance.service.ContractOfferConverter;
import com.inzynieria.insurance.service.SendMailService;
import com.inzynieria.insurance.service.GeneratePaymentsService;
import com.inzynieria.insurance.service.NotificationCreator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * ContractController zajmuje się przechwytywaniem żądań powiązanych z umowami. Umożliwia odbiór żądań przysyłanych z AngularaJS.
 */
@RestController
@RequestMapping("/contract")
public class ContractController {

    /**
     * Finalny statyczny obiekt loggera służący do wyświetlania informacji o czasie oraz miejscu wystpienia błędu w konsoli lub w pliku.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ContractController.class);

    /**
     * Repozytorium wniosków
     */
    @Autowired
    ContractRepository contractRepository;
    /**
     * Repozytorium ofert
     */
    @Autowired
    OfferRepository offerRepository;
    /**
     *
     */
    @Autowired
    ContractOfferConverter contractOfferConverter;
    /**
     * Repozytorium użytkowników
     */
    @Autowired
    UserRepository userRepository;


    @Autowired
    GeneratePaymentsService generatePaymentsService;

    /**
     * Zajmuje się odbiorem i obsługą żądania dotyczącego tworzenia nowych umów.
     * @param contract Ciało żądania zawiera obiekt umowy, którą będziemy dodawać do naszego systemu.
     * @return Zwraca informacje, o pozytywnym dodaniu umowy w przypadku powodzenia, w przypadku błędu, zwraca informacje o niepowodzeniu.
     */
    @RequestMapping(value="/add", method = RequestMethod.POST)
    public String createContract(@RequestBody Contract contract){
        LOGGER.info("Dodaje kontrakt:  idOferty: "+contract.getIdOffer()+" Data ważności:"+ contract.getExpirationDate()+", data stworzenia: "+contract.getStartDate()+" idUsera: "+contract.getIdUser());
        Contract saved= contractRepository.save(contract);
        if(saved!=null) {
            generatePaymentsService.generatePayments(saved.getIdContract(), saved.getIdUser(), saved.getIdOffer(),saved.getStartDate() );
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipalName = authentication.getName();
            User user = userRepository.findByUsername(currentPrincipalName);
            SendMailService sendMailService = new SendMailService(user.getEmail(), "Dodano nową umowę o numerze: "+ contract.getIdContract()+ " \nDziękujemy za skorzystanie z usług naszej agencji ubezpieczeniowej", "Potwierdzenie utworzenia umowy");
            sendMailService.send();
            return "Zarejestrowano pomyślnie";
        }
        else
            return "Wystąpił błąd podczas dodawania umowy!";
    }

    /**
     * Metoda ktora ma na celu znalezienie kontraktu w bazie danych
     * @param id kontraktu którego szukamy w naszej bazie danych
     * @return
     */

    @RequestMapping(value="/find/{id}", method = RequestMethod.GET)
    public Boolean findContract(@PathVariable(value="id") Integer id){
        LOGGER.info("Przyjąłem id: "+id);
        List<Contract> contracts= contractRepository.findContractByIdUser(id);
        LOGGER.info("Liczba znalezionych kontraktów: "+ contracts.size());
        return !contracts.isEmpty();
    }

    /**
     * Metoda odpowiedzialna za wyszukanie kontraktu
     * @param id id kontraktu ktorego szukamy
     * @return zwraca wyszukany kontrakt
     */

    @RequestMapping(value="/get/{id}", method = RequestMethod.GET)
    public Contract getContract(@PathVariable(value="id") Integer id){
        Contract contract= contractRepository.findOne(id);
        return contract;
    }

    /**
     * Zajmuje się odbiorem i obsługą żądania dotyczącego przeglądania umów wraz z ofertą.
     * @param userId Identyfikator użytkownika
     * @return Lista kontraktów oraz powiązanej z nim oferty
     */
    @RequestMapping(value="/getList/{userId}", method = RequestMethod.GET)
    public List<ContractWithOffer> getContractsWithOffers(@PathVariable(value="userId") Integer userId){
        LOGGER.info("Przyjąłem id: "+userId);
        List<Contract> contracts= contractRepository.findContractByIdUser(userId);
        List<ContractWithOffer> contractWithOffers = new ArrayList<>();
        for(Contract contract : contracts)
        {
            contractWithOffers.add(contractOfferConverter.convert(contract, offerRepository.findOne(contract.getIdOffer())));
        }

        return contractWithOffers;
    }



    @RequestMapping(value="/check", method = RequestMethod.POST)
    public ResponseEntity checkInsurance(@RequestBody String pesel){
        LOGGER.info("Podbrany pesel: "+pesel);
        try {
            List<Contract> contracts = contractRepository.findContractByPesel(pesel);
            List<ContractWithOffer> contractWithOffers = new ArrayList<>();
            for(Contract contract : contracts)
            {
                contractWithOffers.add(contractOfferConverter.convert(contract, offerRepository.findOne(contract.getIdOffer())));
            }
            if(contractWithOffers.isEmpty())
            {
                LOGGER.info("Zwracany błąd ");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Brak użytkownika w bazie danych...");
            }
            return ResponseEntity.status(HttpStatus.OK).body(contractWithOffers);
        }catch(Exception e)
        {
            LOGGER.info("Zwracany błąd ");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nieudany odczyt z bazy danych...");
        }
    }

}
