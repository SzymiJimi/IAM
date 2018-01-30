package com.inzynieria.insurance.controller;

import com.inzynieria.insurance.model.ClientData;
import com.inzynieria.insurance.repository.ClientDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ContractController zajmuje się przechwytywaniem żądań powiązanych z danymi klientów. Umożliwia odbiór żądań przysyłanych z AngularaJS.
 */
@RestController
@RequestMapping(value="/clientData")
public class ClientDataController {
    /**
     * Finalny statyczny obiekt loggera służący do wyświetlania informacji o czasie oraz miejscu wystpienia błędu w konsoli lub w pliku.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientDataController.class);
    /**
     * Repozytorium danych klienta
     */
    @Autowired
    ClientDataRepository clientDataRepository;

    /**
     *  Zajmuje się odbiorem i obsługą żądania dotyczącego tworzenia nowych danych klienta.
     * @param clientData Ciało żądania zawiera obiekt danych klienta, który będziemy dodawać do naszego systemu.
     * @return Zwraca informacje, o pozytywnym dodaniu danych użytkownika w przypadku powodzenia, w przypadku błędu, zwraca informacje o niepowodzeniu.
     */
    @RequestMapping(value="/add", method = RequestMethod.POST)
    public String createClientData(@RequestBody ClientData clientData){
        LOGGER.info("Jestem tutaj ");
//        LOGGER.info("Dodaje kontrakt:  idOferty: "+clientData.getIdOffer()+" Data ważności:"+ contract.getExpirationDate()+", data stworzenia: "+contract.getStartDate()+" idUsera: "+contract.getIdUser());
        LOGGER.info("Dodaje client data: "+clientData.toString());
        if(clientDataRepository.save(clientData)!=null) {
            return "Zarejestrowano pomyślnie";
        }
        else
            return "Wystąpił błąd";
    }
    /**
     * Metoda zajmująca się odbiorem żądania dotyczącego znalezienia danych użytkownika w bazie o podanym ID użytkownika
     * @param id Id użytkownika, którego dane chcemy wyświetlić
     * @return Zwraca znaleziony obiekt danych klienta
     */
    @RequestMapping(value="/find/{id}", method = RequestMethod.GET)
    public ClientData findClientData(@PathVariable(value="id") Integer id){
        LOGGER.info("Przyjąłem id: "+id);
        ClientData clientData= clientDataRepository.findClientDataByUserId(id);
        return clientData;
    }

}
