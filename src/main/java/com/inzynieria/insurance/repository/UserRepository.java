package com.inzynieria.insurance.repository;

import com.inzynieria.insurance.model.User;
import javafx.application.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Interfejs rozszerzający JpaRepository umożliwia pobieranie danych z encji user z bazy danych w prosty i przejrzysty sposób.
 */
@Service
public interface UserRepository extends JpaRepository<User,Integer> {
    /**
     * Wyszukiwanie użytkownika po imieniu
     * @param name nazwa
     * @return Użytkownik, którego imię jest podane
     */
    User findUserByName(String name);

    /**
     * Wyszukiwanie użytkownika po nazwie
     * @param username nazwa użytkownika
     * @return użytkownik, którego nazwa została podana
     */
    User findByUsername(String username);

    /**
     * Wyszukiwanie użytkowników po imieniu lub nazwisku lub nazwie użytkownika lub e-mailu
     * @param name imię użytkownika
     * @param surname nazwisko użytkownika
     * @param username nazwa użytkownika
     * @param Email e-mail użytkownika
     * @return Lista użytkowników
     */
    List<User> findUserByNameOrSurnameOrUsernameOrEmail(String name, String surname, String username, String Email);

    @Query(value= "SELECT  u.idUser, u.username, u.password, u.name, u.surname, u.email from User u, UserRoles, role where u.idUser=userRoles.USER_IDUSER AND userRoles.ROLE_IDROLE=role.idRole AND  ( u.username= :data OR u.name= :data OR u.surname= :data OR u.email= :data)  AND role.name = 'ROLE_CLIENT'", nativeQuery = true)
    List<User> findClient(@Param("data") String data);

    /**
     * Wyszukiwanie jednego klienta po numerze ID
     * @param data numer ID
     * @return Klient o podanym numerze ID
     */
    @Query(value= "SELECT  u.idUser, u.username, u.password, u.name, u.surname, u.email from User u, UserRoles, role where u.idUser=userRoles.USER_IDUSER AND userRoles.ROLE_IDROLE=role.idRole AND u.idUser= :data  AND role.name = 'ROLE_CLIENT'", nativeQuery = true)
    User findOneClient(@Param("data") Integer data);


//    @Query(value= "SELECT  u.idUser, u.username, u.password, u.name, u.surname, u.email, u.idRole from User u, UserRoles where u.idUser=userRoles.USER_IDUSER AND userRoles.name = 'ROLE_CLIENT'", nativeQuery = true)
//    List<User> findAllClients();


}
