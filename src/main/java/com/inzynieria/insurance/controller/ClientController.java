package com.inzynieria.insurance.controller;

import com.inzynieria.insurance.dto.UserDto;
import com.inzynieria.insurance.model.ClientData;
import com.inzynieria.insurance.model.User;
import com.inzynieria.insurance.repository.ClientDataRepository;
import com.inzynieria.insurance.repository.UserRepository;
import com.inzynieria.insurance.service.UserConv;
import com.inzynieria.insurance.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.xml.bind.ValidationException;
import java.util.List;

/**
 * ClientController zajmuje się przechwytywaniem żądań powiązanych z klientami agencji ubezpieczoniowej. Umożliwia odbiór żądań przysyłanych z AngularaJS.
 */
@RestController
@RequestMapping(value="/client")
public class ClientController {
    /**
     * Finalny statyczny obiekt loggera służący do wyświetlania informacji o czasie oraz miejscu wystpienia błędu w konsoli lub w pliku.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);
    /**
     * Serwis użytkownika
     */
    @Autowired
    UserService userService;
    /**
     * Repozytorium użytkownika
     */
    @Autowired
    UserRepository userRepository;
    /**
     * Repozytorium danych klienta
     */
    @Autowired
    ClientDataRepository clientDataRepository;


    @RequestMapping(value="/showData")
    public String show()
    {
        LOGGER.info("Klient wyswietla dane oferty");
        return "";
    }


    @RequestMapping(value = "/show")
    public UserDto setClientData(@RequestBody Integer value) {
        User user = userRepository.findOne(value);
        UserConv userConv = new UserConv();
        UserDto userDto=  userConv.convertUserToUserDto(user);
        return userDto;
    }

    /**
     * Metoda zajmująca się odbiorem żądania dotyczącego znalezienia klienta w bazie o konkretnym id.
     * @param id Id użytkownika, którego chcemy znaleźć w bazie.
     * @return
     */
    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public ModelAndView showUser(@PathVariable(value="id") Integer id)
    {
        ModelAndView mav = new ModelAndView("/client/clientData");
        return mav;
    }

    /**
     *
     * @param value string po którym
     * @return
     * @throws ValidationException
     */


    @RequestMapping(value="/find")

    public List<UserDto> findClients(@RequestBody String value) throws ValidationException {
        LOGGER.info("Klient szuka klienta o nazwie: "+ value);
        List<UserDto> users= userService.findClient(value);
        return users;
    }


    @RequestMapping(value="/findOne/{idUser}", method = RequestMethod.GET)
    public UserDto findOneById(@PathVariable Integer idUser) throws ValidationException {
        LOGGER.info("Jestem tutaj, value: "+ idUser);
        UserDto users= userService.findOneClient(idUser);
        return users;
    }

    /**
     * Metoda zajmująca się odbiorem żądania dotyczącego wyświetlenia wszystkich klientów w bazie.
     * @return Zwraca znalezionych klientów.
     * @throws ValidationException
     */
    @RequestMapping(value="/getList")
    public ResponseEntity getUserList(){
        try{
           List<ClientData> clients=  clientDataRepository.findAllClients();

            return ResponseEntity.status(HttpStatus.OK).body(clients);
        }catch(Exception e )
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nie znaleziono userów...");
        }

    }

}
