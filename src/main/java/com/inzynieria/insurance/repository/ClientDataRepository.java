package com.inzynieria.insurance.repository;

import com.inzynieria.insurance.model.ClientData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * Interfejs rozszerzający JpaRepository umożliwia pobieranie danych z encji clientdata z bazy danych w prosty i przejrzysty sposób.
 */
@Service
public interface ClientDataRepository extends JpaRepository<ClientData,Integer> {
    /**
     * Wyszukiwanie danych klientów po id użytkownika
     * @param id id użytkownika
     * @return Dane użytkownika
     */
    ClientData findClientDataByUserId(Integer id);

    /**
     * Wyszukiwanie klientów
     * @return Zwraca listę klientów
     */
    @Query(value= "SELECT  c.idClientData, c.userId, c.idNumber, c.pesel, c.phone, c.place, c.houseNr, c.flatNr, c.street from ClientData c, User,  UserRoles, Role where c.userId=User.idUser AND User.idUser=userRoles.USER_IDUSER AND userRoles.ROLE_IDROLE=Role.idRole  AND role.name = 'ROLE_CLIENT'", nativeQuery = true)
    List<ClientData> findAllClients();

}
