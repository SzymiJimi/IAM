package com.inzynieria.insurance.repository;

import com.inzynieria.insurance.model.Notification;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Interfejs rozszerzający JpaRepository umożliwia pobieranie danych z encji notification z bazy danych w prosty i przejrzysty sposób.
 */
@Service
public interface NotificationRepository extends JpaRepository<Notification,Integer> {
    /**
     * Wyszukiwanie zgłoszenia po numerze PESEL
     * @param data numer PESEL użytkownika, który utworzył zgłoszenie
     * @return Lista zgłoszeń
     */
    @Query(value= "SELECT  n.idNotification, n.type, n.status, n.incident_time, n.incident_description, n.valuation, n.idContract from Notification n, contract,user, clientdata where n.idContract=contract.idContract AND contract.idUser=user.idUser AND user.idUser=clientData.userId AND  clientdata.pesel=:data", nativeQuery = true)
    List<Notification> findNotificationsByUserPesel(@Param("data") String data);
}