package com.inzynieria.insurance.repository;

import com.inzynieria.insurance.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Interfejs rozszerzający JpaRepository umożliwia pobieranie danych z encji contract z bazy danych w prosty i przejrzysty sposób.
 */
@Service
public interface ContractRepository extends JpaRepository<Contract,Integer> {
    /**
     * Wyszukiwanie umów po id użytkownika
     * @param id id użytkownika
     * @return Lista umów użytkownika
     */
    List<Contract> findContractByIdUser(Integer id);

    /**
     * Wyszukiwanie umów po numerze PESEL
     * @param data numer PESEL
     * @return Lista umów
     */
    @Query(value= "SELECT  c.idContract, c.expirationDate, c.idOffer, c.idUser, c.startDate from contract c, clientdata where c.idUser= clientData.userId AND  clientdata.pesel= :data", nativeQuery = true)
    List<Contract> findContractByPesel(@Param("data") String data);

}
