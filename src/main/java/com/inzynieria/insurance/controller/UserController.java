package com.inzynieria.insurance.controller;


import com.inzynieria.insurance.dto.RoleDto;
import com.inzynieria.insurance.dto.UserDto;
import com.inzynieria.insurance.model.Role;
import com.inzynieria.insurance.model.User;
import com.inzynieria.insurance.model.UserRoles;
import com.inzynieria.insurance.model.UserRolesPK;
import com.inzynieria.insurance.repository.RoleRepository;
import com.inzynieria.insurance.repository.UserRepository;
import com.inzynieria.insurance.repository.UserRolesRepository;
import com.inzynieria.insurance.service.SendMailService;
import com.inzynieria.insurance.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * User controller zajmuje się przechwytywaniem żądań powiązanych z użytkownikami. Umożliwia odbiór żądań przysyłanych z AngularaJS.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    /**
     * Finalny statyczny obiekt loggera służący do wyświetlania informacji o czasie oraz miejscu wystpienia błędu w konsoli lub w pliku.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    /**
     * Repozytorium użytkownika
     */
    @Autowired
    UserRepository userRepository;

    /**
     * Serwis użytkownika
     */
    @Autowired
    UserService userService;
    /**
     * Repozytorium roli, które może przyjmować użytkownik
     */
    @Autowired
    RoleRepository roleRepository;
    /**
     * Repozytorium roli, które przyjmują konkretni użytkownicu
     */
    @Autowired
    UserRolesRepository userRolesRepository;

    /**
     * Wykorzystywane do szyfrowania hasła
     */
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     *  Zajmuje się odbiorem i obsługą żądania dotyczącego tworzenia nowego użytkownika do systemu.
     * @param user Ciało żądania zawiera obiekt usera, którego będziemy dodawać do naszego systemu.
     * @return Zwraca informacje, o pozytywnym zarejestrowaniu w przypadku powodzenia, w przypadku błędu, zwraca informacje o niepowodzeniu.
     */
    @RequestMapping(value="/add", method = RequestMethod.POST)

    public String createUser(@RequestBody User user){

        LOGGER.info("Dodawanie informacji użytkownika do bazy");
        String password= user.getPassword();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User userRet =userRepository.save(user);
        UserRoles userRoles = new UserRoles("Nowy",userRet.getIdUser() ,12);
        try{
            userRolesRepository.save(userRoles);
            SendMailService sendMailService = new SendMailService(user.getEmail(), "Witamy w agencji ubezpieczeniowej "+ user.getName()+ " " + user.getSurname()+
                    "\nTwoje dane do logowania to: \nLogin: "+ user.getUsername()+ "\nhasło: "+password+"\nZalecana jest zmiana hasła po pierwszym zalogowaniu", "Witaj w IAM" );
            sendMailService.send();
            return "Zarejestrowano pomyślnie";
        }catch(Exception e){

            LOGGER.info("Nie udało się utworzyć nowego użytkownika...");
            LOGGER.info(e.toString());
            return "Nie udało się utworzyć nowego użytkownika...";
        }

    }

    /**
     * Kontroler zajmujący się odbiorem żądania przysyłanego z widoku, które dotyczy aktualizacji użytkownika.
     * @param id Id aktualizowanego użytkownika.
     * @param username Nowa nazwa aktualizowanego użytkownika pobierana ze ścieżki.
     * @return Zwraca informacje, o pozytywnym zaaktualizowaniu w przypadku powodzenia. W przypadku błędu, zwraca informacje o niepowodzeniu.
     * @throws ValidationException
     */
    @RequestMapping(value="/update/{id}/{username}")
    public String updateUser(@PathVariable(value="id") Integer id, @PathVariable(value="username") String username) throws ValidationException {

        LOGGER.info("Update użytkownika w bazie o ID= "+id);
        User user= userRepository.findOne(id);
        UserDto userToUpdate= new UserDto();
        userToUpdate.setIdUser(user.getIdUser());
        userToUpdate.setUsername(username);
        userToUpdate.setPassword("haslo");
        userToUpdate.setName("Nowak");
        userToUpdate.setSurname("Malinowska");
        userToUpdate.setEmail("anna@wp.pl");
        userToUpdate.setRoles(new ArrayList<>(0));
        try{
            userService.updateUser(userToUpdate, id);
            return "Zaaktualizowano pomyślnie";
        }catch (Exception e)
        {
            return "Błąd aktualizowania nowego użytkownika";
        }
    }


    /**
     * Metoda zajmująca się odbiorem żądania dotyczącego znalezienia użytkownika w bazie o konkretnym id.
     * @param id Id użytkownika, którego chcemy znaleźć w bazie.
     * @return Zwraca znaleziony obiekt usera.
     * @throws ValidationException
     */
    @RequestMapping(value="/findOne/{id}")
    public User findOneUserById(@PathVariable(value="id") Integer id) throws ValidationException {
        User user=  userRepository.findOne(id);
        user.setRoles(null);
        return user;
    }

    /**
     * Metoda zajmująca się odbiorem żądania dotyczącego znalezienia listy użytkowników, których email, imię, nazwisko bądź nazwa użytkownika jest
     * taka samama jak przekazany parametr value.
     * @param value Parametr przekazany w ciele żądania, który zawiera ciąg znaków, który będzie porównywany do emaila, imienia, nazwiska bądź nazwy użytkownika.
     * @return Zwraca listę znalezionych użytkowników.
     * @throws ValidationException
     */
    @RequestMapping(value="/find")
    public List<User> findUser(@RequestBody String value) throws ValidationException {
        return userService.findUser(value);
    }

    /**
     * Metoda zajmująca się odbiorem żądania typu GET dotyczącego znalezienia aktualnie zalogowanego użytkownika.
     * @return Zwraca aktualnie zalogowanego użytkownika.
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET )
    public UserDto getLoggedUser() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name= auth.getName();
        User user = userRepository.findByUsername(name);

        Set<Role> set;
        set = user.getRoles();

        Role [] array =  set.toArray(new Role[0]);
        List<RoleDto> list = new ArrayList<>();
        RoleDto roleDto =new RoleDto();

        for (Role role : array) {
            roleDto.setIdRole(role.getIdRole());
            roleDto.setName(role.getName());
            list.add(new RoleDto(role.getIdRole(), role.getName()));
        }

        return new UserDto(user.getIdUser(), user.getUsername(), "#", user.getName(), user.getSurname(), user.getEmail(), list);
    }

    @RequestMapping(value="/checkOldPass")
    public ResponseEntity checkOldPass(@RequestBody String value) {
        try{

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name= auth.getName();
            User loggedUser = userRepository.findByUsername(name);
//            LOGGER.info("Hasło z bazy: "+loggedUser.getPassword() );
//            LOGGER.info("Hasło podane: "+value );
            if(passwordEncoder.matches(value, loggedUser.getPassword()))
            {
                return ResponseEntity.status(HttpStatus.OK).body("Stare hasło poprawne!");
            }else {
                throw new Exception();
            }
        }catch(Exception e) {
            LOGGER.info("Niepoprawne stare hasło...");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Niepoprawne stare hasło...");
        }
    }


    @RequestMapping(value="/changePass")
    public ResponseEntity changePass(@RequestBody String pass) {

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getName();
            User loggedUser = userRepository.findByUsername(name);

            UserDto userToUpdate = new UserDto();
            userToUpdate.setIdUser(loggedUser.getIdUser());
            userToUpdate.setUsername(loggedUser.getUsername());
            String newPass= passwordEncoder.encode(pass);
            LOGGER.info("Nowe hasło niezakodowane: "+ pass+"--");
            LOGGER.info("Nowe hasło zakodowane: "+ newPass);
            if(passwordEncoder.matches(pass, newPass))
             {
            LOGGER.info("Hasła pasują...");
            }else {
                LOGGER.info("Hasła niepasują...");

            }
            userToUpdate.setPassword(passwordEncoder.encode(pass));
            userToUpdate.setName(loggedUser.getName());
            userToUpdate.setSurname(loggedUser.getSurname());
            userToUpdate.setEmail(loggedUser.getEmail());
            userToUpdate.setRoles(new ArrayList<>());
            try {
                userService.updatePassUser(userToUpdate, loggedUser.getIdUser(), loggedUser.getRoles());
                return ResponseEntity.status(HttpStatus.OK).body("Zaaktualizowano pomyślnie!");
            } catch (Exception e) {
                LOGGER.info("Nie można zaaktualizować: "+e.toString());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Błąd aktualizacji...");

            }

    }

    @RequestMapping(value="/getPerks")
    public ResponseEntity getPerks() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name= auth.getName();
        try{

            User loggedUser = userRepository.findByUsername(name);
            userRolesRepository.deleteData(loggedUser.getIdUser());
            UserRoles userRoles = new UserRoles("Aktualizacja",loggedUser.getIdUser() ,11);
            UserRoles userRoles1 = new UserRoles("Aktualizacja",loggedUser.getIdUser() ,15);
            userRolesRepository.save(userRoles);
            userRolesRepository.save(userRoles1);
            return ResponseEntity.status(HttpStatus.OK).body("Zaaktualizowano pomyślnie!");

        }catch(Exception e) {
            LOGGER.info(e.toString());
            LOGGER.info("Nie udało się zaaktualizować");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nie udało się zaaktualizować...");
        }
    }

    @RequestMapping(value="/removePerks")
    public ResponseEntity removePerks() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name= auth.getName();
        try{
            User loggedUser = userRepository.findByUsername(name);
            userRolesRepository.deleteData(loggedUser.getIdUser());
            UserRoles userRoles = new UserRoles("Aktualizacja",loggedUser.getIdUser() ,12);
            userRolesRepository.save(userRoles);
            return ResponseEntity.status(HttpStatus.OK).body("Zaaktualizowano pomyślnie!");

        }catch(Exception e) {
            LOGGER.info(e.toString());
            LOGGER.info("Nie udało się zaaktualizować");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nie udało się zaaktualizować...");
        }
    }



}
